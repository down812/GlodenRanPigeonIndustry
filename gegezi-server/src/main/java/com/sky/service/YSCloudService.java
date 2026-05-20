package com.sky.service;


import com.sky.dto.CameraDTO;
import com.sky.dto.YSCloudDTO;

import java.util.List;

public interface YSCloudService {
    /**
     * 获取摄像头直播地址
     * @param deviceSerial 设备序列号
     * @param channelNo 通道号
     * @return 摄像头信息，包含直播地址
     */
    String getCameraLiveAddress(String deviceSerial, int channelNo);
    
    /**
     * 添加设备到萤石云平台
     * @param deviceSerial 设备序列号
     * @param validateCode 设备验证码
     * @return 是否添加成功
     */
    boolean addDevice(String deviceSerial, String validateCode);


    List<YSCloudDTO> processDeviceUrls(String deviceName);
}