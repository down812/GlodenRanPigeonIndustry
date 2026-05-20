package com.sky.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class AnnualSocialFunctions implements Serializable {
    private Integer id;

    private String name;

    private Integer numbers;

    private static final long serialVersionUID = 1L;
}