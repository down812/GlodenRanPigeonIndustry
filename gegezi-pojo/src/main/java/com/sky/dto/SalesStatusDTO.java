package com.sky.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SalesStatusDTO {
    private String name;
    private ArrayList<String> month;
    private Integer[] money;
}
