package com.sky.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class YSCloudUtil {
    private static final String BASE_URL = "https://open.ys7.com/api/lapp";
    private static final RestTemplate restTemplate = new RestTemplate();

    /**
     * 获取萤石云访问令牌
     * @param appKey 应用AppKey
     * @param appSecret 应用AppSecret
     * @return 访问令牌
     */
    public static JSONObject getAccessToken(String appKey, String appSecret) {
        String url = BASE_URL + "/token/get";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("appKey", appKey);
        map.add("appSecret", appSecret);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        log.info("获取萤石云访问令牌响应: {}", response.getBody());
        
        return JSON.parseObject(response.getBody());
    }
    
    /**
     * 获取设备的直播地址
     * @param accessToken 访问令牌
     * @param deviceSerial 设备序列号
     * @param channelNo 通道号，IPC设备填1
     * @return 直播地址信息
     */
    public static JSONObject getLiveAddress(String accessToken, String deviceSerial, int channelNo) {
        String url = BASE_URL + "/v2/live/address/get";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("accessToken", accessToken);
        map.add("deviceSerial", deviceSerial);
        map.add("channelNo", String.valueOf(channelNo));
        map.add("protocol", "2"); //流播放协议，1-ezopen、2-hls、3-rtmp、4-flv，默认为1
        map.add("quality", "2"); // 1-高清，2-标清
        map.add("expireTime","864000"); //设置有效期10天
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        log.info("获取设备直播地址响应: {}", response.getBody());
        
        return JSON.parseObject(response.getBody());
    }
    
    /**
     * 添加设备
     * @param accessToken 访问令牌
     * @param deviceSerial 设备序列号
     * @param validateCode 设备验证码
     * @return 添加结果
     */
    public static JSONObject addDevice(String accessToken, String deviceSerial, String validateCode) {
        String url = BASE_URL + "/device/add";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("accessToken", accessToken);
        map.add("deviceSerial", deviceSerial);
        map.add("validateCode", validateCode);
        
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        log.info("添加设备响应: {}", response.getBody());
        
        return JSON.parseObject(response.getBody());
    }
}