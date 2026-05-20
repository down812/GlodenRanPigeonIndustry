package com.sky.dto;


import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Data
public class BaseGreenhouseDTO {
        private List<String> name; // 基地大棚生产信息
        private List<Float> numbers;    // 数量

        public void setName(String name) {
            this.name = Arrays.stream(name.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }

    public void setNumbers(String numbers) {
        this.numbers = Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Float::valueOf)  // 改为解析为Float
                .collect(Collectors.toList());
    }
}

