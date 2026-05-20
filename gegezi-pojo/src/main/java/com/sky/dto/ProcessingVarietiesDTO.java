package com.sky.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProcessingVarietiesDTO implements Serializable{

    private String name;

    private Integer numbers;
}
