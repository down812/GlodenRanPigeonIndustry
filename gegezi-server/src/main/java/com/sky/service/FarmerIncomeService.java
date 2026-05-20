package com.sky.service;


import com.sky.dto.FarmerIncomeDTO;
import com.sky.entity.FarmerIncome;

/**
* @author 谭晋曦
* @description 针对表【farmer_income】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface FarmerIncomeService {

    FarmerIncomeDTO getFarmerIncome(String name);

    boolean updateFarmerIncome(FarmerIncome farmerIncome);

    boolean insertFarmerIncome(FarmerIncome farmerIncome);

    boolean deleteFarmerIncome(String name, String time);
}
