package com.sky.service;


import com.sky.dto.MonthlySalesTrendDTO;
import com.sky.entity.SalesTrend;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【sales_trend】的数据库操作Service
* @createDate 2025-02-23 17:06:11
*/
public interface SalesTrendService {


    MonthlySalesTrendDTO getSaleTrend(String name);

    boolean updateSaleTrend(SalesTrend salesTrend);

    boolean insertSaleTrend(SalesTrend salesTrend);

    boolean deleteSaleTrend(String time, String name);
}
