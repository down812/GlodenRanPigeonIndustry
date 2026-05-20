package com.sky.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CameraDTO implements Serializable {
//    private Long id;
    private String serial;    // 对应数据库serial字段（设备序列号）
    private Integer no;       // 对应数据库no字段（通道号）

    private String name;
    private String url;  // HLS直播地址

//    private String serial;
//    private Integer no;
//    private Integer status;
//    private Integer isencrypt;
//    private String rtmp; // RTMP直播地址
}