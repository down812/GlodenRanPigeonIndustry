package com.sky.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MonthlySalesTrendDTO {
    private String name;
    private ArrayList<String> month;
    private Integer[] price;
}
