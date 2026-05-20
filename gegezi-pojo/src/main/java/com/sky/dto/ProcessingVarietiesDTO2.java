package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel
public class ProcessingVarietiesDTO2 implements Serializable {
    @ApiModelProperty(value = "给3个种类名称", required = true,  example ="[\"乳鸽\", \"肉鸽\", \"冰鲜鸽\"]")
    private List<String> name;


    @ApiModelProperty(value = "给对应种类的数量", required = true, example = "[100,200,300]")
    private List<Integer> numbers;
}