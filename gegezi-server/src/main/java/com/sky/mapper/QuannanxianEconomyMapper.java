package com.sky.mapper;


import com.sky.entity.QuannanxianEconomy;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 谭晋曦
* @description 针对表【quannanxian_economy】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.QuannanxianEconomy
*/
@Mapper
public interface QuannanxianEconomyMapper {

    QuannanxianEconomy getGDEcono();

    int updateGDEcono(QuannanxianEconomy quannanxianEconomy);
}




