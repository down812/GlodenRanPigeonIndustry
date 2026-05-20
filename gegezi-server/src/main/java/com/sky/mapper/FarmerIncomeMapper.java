package com.sky.mapper;


import com.sky.entity.FarmerIncome;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【farmer_income】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.FarmerIncome
*/
@Mapper
public interface FarmerIncomeMapper {

    List<FarmerIncome> getFarmerIncome(String name);

    int updateFarmerIncome(FarmerIncome farmerIncome);

    int insertFarmerIncome(FarmerIncome farmerIncome);

    int deleteFarmerIncome(String name, String time);

    FarmerIncome getFarmerIncomeSin(FarmerIncome farmerIncome);
}




