package com.sky.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BaseDoveDestinationDTO {
    private List<String> name;
    private List<Integer> numbers;

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
