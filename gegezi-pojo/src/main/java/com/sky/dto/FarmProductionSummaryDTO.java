package com.sky.dto;


import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FarmProductionSummaryDTO {
    private String name;           // 种类名称（种鸽/乳鸽）
    private List<Integer> totalNumbers; // 数量列表
    private List<Integer> year;    // 年份列表

    // 修正setter方法，移除多余的substring切割
    public void setTotalNumbers(String totalNumbers) {
        this.totalNumbers = Arrays.stream(totalNumbers.split(",")) // 直接分割字符串
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public void setYear(String year) {
        this.year = Arrays.stream(year.split(",")) // 直接分割字符串
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
