package com.sky.entity;

import lombok.Data;

@Data
public class BaseGreenhouse {
    private Integer id;
    private Integer numberOfBreedingPairs;  // 种鸽数量
    private Integer numberOfPoultry;       // 乳鸽数量
    private Integer numberOfEggs;          // 鸽蛋数量
    private Integer totalEggProduction;    // 总产量
}
