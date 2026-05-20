package com.sky.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.util.ArrayList;

@Data
public class YearFarmDTO {
    private String name;
    private ArrayList<Integer> year;
    private Integer[] seed;
    private Integer[] milk;
}
