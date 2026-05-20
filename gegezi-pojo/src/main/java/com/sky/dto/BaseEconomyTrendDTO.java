package com.sky.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BaseEconomyTrendDTO {
    private ArrayList<Integer> year;
    private ArrayList<Integer> money;
}
