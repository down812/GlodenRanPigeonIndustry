package com.sky.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class SalesTrend implements Serializable {
    private Integer id;

    private String name;

    private Integer price;

    private Integer sale;

    private String time;

    private static final long serialVersionUID = 1L;
}