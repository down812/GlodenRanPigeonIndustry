package com.sky.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class CameraDTO1 implements Serializable {
    private Long deviceId;
    private String CameraId;
    private String Name;
    private String Serial;
    private Integer No;
    private Integer Status;
    private Integer IsEncrypt;
    private String Hls;
    private String Rtmp;
    private Integer IsDeleted;
    private Integer Version;

}