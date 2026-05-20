package com.sky.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BasePersonDeepDTO {
    private List<String> name; // 深加工种类列表
    private List<Integer> numbers;    // 年份列表

    public void setName(String name) {
        this.name = Arrays.stream(name.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public void setNumbers(String numbers) {
        this.numbers = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
