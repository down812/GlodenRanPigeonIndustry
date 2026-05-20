package com.sky.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class Camera implements Serializable {
    private Long id;

    private Long deviceId;

    private String cameraid;

    private String name;

    private String serial;

    private Integer no;

    private Integer status;

    private Integer isencrypt;

    private String hls;

    private String rtmp;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private Integer version;

    private static final long serialVersionUID = 1L;
}