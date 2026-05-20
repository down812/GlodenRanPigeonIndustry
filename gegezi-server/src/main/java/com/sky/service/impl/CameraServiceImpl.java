package com.sky.service.impl;


import com.sky.dto.CameraDTO;
import com.sky.dto.YSCloudDTO;
import com.sky.mapper.CameraMapper;
import com.sky.service.CameraService;
import com.sky.service.YSCloudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【camera(摄像头表)】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Slf4j
@Service
public class CameraServiceImpl implements CameraService {
//
//    @Autowired
//    CameraMapper cameraMapper;
//
//    @Autowired
//    YSCloudService ysCloudService;
//
//
//    @Override
//    public List<YSCloudDTO> processAllDevices() {
//        List<YSCloudDTO> result = new ArrayList<>();
//        // 改用Arrays.asList保证兼容性
//        List<String> deviceNames = Arrays.asList("棚顶", "其他设备");
//
//        for (String name : deviceNames) {
//            try {
//                //1 根据name获取对应的serial和no
//                List<CameraDTO> devices = cameraMapper.selectSerialAndNo();
//                for (CameraDTO device : devices) {
//                //2 通过seial和no查出每一个对应的url
//                    CameraDTO liveInfo = ysCloudService.getCameraLiveAddress(device.getSerial(), device.getNo());
//                //3 更新数据库的url
//                    cameraMapper.updateHlsUrl(liveInfo.getUrl(), device.getSerial(), device.getNo());
//                }
//            } catch (Exception e) {
//                log.error("处理设备{}失败: {}", name, e.getMessage());
//            }
//        }
//        return result;
//    }


}




