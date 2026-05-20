package com.sky.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SlaughterhouseDTO implements Serializable {
    private List<String> timeData;
    private List<Integer> countData;

}
