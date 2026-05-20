package com.sky.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// ... 其他代码保持不变 ...

@Data
public class QuarterDTO implements Serializable {
    private List<Integer> numbers1; // 乳鸽出栏数（四位数组对应四个季度）
    private List<Integer> numbers2; // 未受精蛋
    private List<Integer> numbers3; // 单蛋
    private List<Integer> numbers4; // 坏蛋
    private List<String> quarters;  // 固定四个季度

    public void setNumbers1(String numbers) {
        this.numbers1 = parseNumbersWithPadding(numbers, 4);
    }

    public void setNumbers2(String numbers) {
        this.numbers2 = parseNumbersWithPadding(numbers, 4);
    }

    public void setNumbers3(String numbers) {
        this.numbers3 = parseNumbersWithPadding(numbers, 4);
    }

    public void setNumbers4(String numbers) {
        this.numbers4 = parseNumbersWithPadding(numbers, 4);
    }

    private List<Integer> parseNumbersWithPadding(String numbers, int size) {
        List<Integer> list = new ArrayList<>(Collections.nCopies(size, 0));
        if (numbers != null && !numbers.isEmpty()) {
            String[] parts = numbers.split(",");
            for (int i = 0; i < Math.min(parts.length, size); i++) {
                list.set(i, Integer.parseInt(parts[i].trim()));
            }
        }
        return list;
    }

    public void setQuarters(String quarters) {
        this.quarters = Arrays.stream(quarters.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}


