package com.sky.service;


import com.sky.dto.QuannanxianSaleDTO;
import com.sky.entity.QuannanxianSale;

/**
* @author 谭晋曦
* @description 针对表【quannanxian_varieties】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface QuannanxianSaleService {

    QuannanxianSaleDTO getSaleRatio();

    boolean updateSale(QuannanxianSale quannanxianSale);

    boolean insertSale(QuannanxianSale quannanxianSale);

    boolean deleteSale(String name);
}
