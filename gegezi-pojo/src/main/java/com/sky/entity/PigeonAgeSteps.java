package com.sky.entity;


import lombok.Data;

import java.io.Serializable;


@Data
public class PigeonAgeSteps implements Serializable {
    private Integer id;

    private String range;

    private Integer numbers;

    private static final long serialVersionUID = 1L;
}