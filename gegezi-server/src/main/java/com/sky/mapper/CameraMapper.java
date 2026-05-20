package com.sky.mapper;


import com.sky.dto.CameraDTO;
import com.sky.dto.YSCloudDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【camera(摄像头表)】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.Camera
*/
@Mapper
public interface CameraMapper {

    /**
     * 获取每个设备的设备序列号和通道号
     * @param
     * @return
     */
    List<CameraDTO> selectSerialAndNo();

    /**
     * 根据设备序列号和通道号存入查出来的直播地址
     * @param url
     * @param serial
     * @param no
     * @return
     */
      int  updateHlsUrl(String url,String serial,Integer no);

    /**
     * 根据设备名字获取 name url
     * @param name
     * @return
     */
    List<YSCloudDTO> selectNameUrl(String name);


}




