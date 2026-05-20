package com.sky.entity;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class IncubationStatus implements Serializable {
    private Integer id;

    private String name;

    private Integer numbers;

    private Date time;

    private static final long serialVersionUID = 1L;
}