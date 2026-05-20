package com.sky.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AgeDTO {

        private List<String> range;  // 接收年龄阶段范围
        private List<Integer> numbers; // 接收对应数量

    public void setrange(String range) {
        this.range = Arrays.stream(range.split(","))
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
