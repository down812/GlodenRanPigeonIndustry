package com.sky.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class ProcessingTypes implements Serializable {
    private Integer id;


    @ApiModelProperty(value = "加工种类名", required = true, example = "红烧")
    private String name;

    private Integer numbers;

    @ApiModelProperty(value = "时间", required = true, example = "2025-02-01")

    private Date time;

    private static final long serialVersionUID = 1L;
}