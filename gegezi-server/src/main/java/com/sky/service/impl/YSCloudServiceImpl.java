package com.sky.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sky.dto.CameraDTO;

import com.sky.dto.YSCloudDTO;
import com.sky.mapper.CameraMapper;
import com.sky.properties.YSCloudProperties;
import com.sky.service.YSCloudService;
import com.sky.utils.YSCloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class YSCloudServiceImpl implements YSCloudService {

    @Autowired
    private YSCloudProperties ysCloudProperties;

    @Autowired
    CameraMapper cameraMapper;


    //当 token 过期时，第三个条件不成立，整个表达式结果为 false
    public boolean isTokenValid() {
        return ysCloudProperties.getAccessToken() != null
                && ysCloudProperties.getAccessTokenExpireTime() != null
                && Instant.now().getEpochSecond() < ysCloudProperties.getAccessTokenExpireTime();
    }

    /**
     * 获取或刷新访问令牌
     */
    private String getAccessToken() {
        // 检查令牌是否过期
        if (!this.isTokenValid()) {
            JSONObject tokenResponse = YSCloudUtil.getAccessToken(
                    ysCloudProperties.getAppKey(),
                    ysCloudProperties.getAppSecret()
            );
            
            if (tokenResponse.getInteger("code") == 200) {
                JSONObject data = tokenResponse.getJSONObject("data");
                ysCloudProperties.setAccessToken(data.getString("accessToken"));
                // 设置过期时间，提前5分钟过期以确保安全
                ysCloudProperties.setAccessTokenExpireTime(
                    Instant.now().getEpochSecond() + data.getLong("expireTime") - 300
                );
            } else {
                log.error("获取萤石云访问令牌失败: {}", tokenResponse);
                throw new RuntimeException("获取萤石云访问令牌失败");
            }
        }
        
        return ysCloudProperties.getAccessToken();
    }


    //根据serial no 获取hls地址->url
    @Override
    public String getCameraLiveAddress(String deviceSerial, int channelNo) {
        String accessToken = getAccessToken();

        JSONObject liveAddressResponse = YSCloudUtil.getLiveAddress(
                accessToken, deviceSerial, channelNo
        );

        if (liveAddressResponse.getInteger("code") == 200) {
            JSONObject data = liveAddressResponse.getJSONObject("data");
            return data.getString("url");
        } else {
            log.error("获取设备直播地址失败: {}", liveAddressResponse);
            throw new RuntimeException("获取设备直播地址失败");
        }
    }
    
    @Override
    public boolean addDevice(String deviceSerial, String validateCode) {
        String accessToken = getAccessToken();
        
        JSONObject addDeviceResponse = YSCloudUtil.addDevice(
            accessToken, deviceSerial, validateCode
        );
        
        if (addDeviceResponse.getInteger("code") == 200) {
            return true;
        } else {
            log.error("添加设备失败: {}", addDeviceResponse);
            return false;
        }
    }



    //第一种情况：自动检查token → 过期则刷新token->查询设备信息 → 获取直播地址 → 更新数据库 → 封装结果返回
    //第二种情况：自动检查token->未过期则直接查询设备信息(不需要再更新token然后获取直播地址和更新数据库)
    @Override
    public List<YSCloudDTO> processDeviceUrls(String deviceName) {

        List<YSCloudDTO> results = new ArrayList<>();

        if (this.isTokenValid()) {
            // 情况二：token未过期，直接返回数据库数据
           results=cameraMapper.selectNameUrl(deviceName);
            log.info("从数据库中查询出来的数据: {}", results);
        } else {
            //获取每个设备的序列号和通道号
            List<CameraDTO> devices = cameraMapper.selectSerialAndNo();
            // 情况一：token过期，执行完整流程
            for (CameraDTO device : devices) {
                String url=this.getCameraLiveAddress(device.getSerial(), device.getNo());
                cameraMapper.updateHlsUrl(device.getUrl(), device.getSerial(), device.getNo());
                results=cameraMapper.selectNameUrl(deviceName);
            }
        }
        return results;
    }

}