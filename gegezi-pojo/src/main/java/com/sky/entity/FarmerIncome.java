package com.sky.entity;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class FarmerIncome implements Serializable {
    private Integer id;

    private String name;

    private BigDecimal numbers;

    private String time;

    private static final long serialVersionUID = 1L;
}