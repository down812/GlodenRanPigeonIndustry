package com.sky.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class FarmerIncomeDTO {
    private String name;
    private ArrayList<Integer> year;
    private BigDecimal[] income;
}
