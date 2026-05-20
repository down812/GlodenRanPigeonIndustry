package com.sky.service;


import com.sky.entity.QuannanxianEconomy;

/**
* @author 谭晋曦
* @description 针对表【quannanxian_economy】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface QuannanxianEconomyService  {

    QuannanxianEconomy getGDEcono();

    boolean updateGDEcono(QuannanxianEconomy quannanxianEconomy);
}
