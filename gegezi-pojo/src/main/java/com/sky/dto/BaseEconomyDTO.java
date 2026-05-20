package com.sky.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BaseEconomyDTO {

    private List<Integer> money; // 经济
    private List<Integer> time;    // 年份列表

    public void setMoney(String money) {
        this.money = Arrays.stream(money.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public void setTime(String time) {
        this.time = Arrays.stream(time.split(","))
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
