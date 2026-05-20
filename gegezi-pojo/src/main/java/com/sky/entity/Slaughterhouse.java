package com.sky.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
public class Slaughterhouse implements Serializable {


    private Integer id;

    @ApiModelProperty(value = "屠宰数", required = true)
    private Integer killNumber;

    @ApiModelProperty(value = "时间", required = true, example = "2025-02-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date time;

    private static final long serialVersionUID = 1L;
}