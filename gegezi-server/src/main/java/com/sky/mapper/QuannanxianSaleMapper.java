package com.sky.mapper;


import com.sky.entity.QuannanxianSale;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【quannanxian_varieties】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.QuannanxianVarieties
*/
@Mapper
public interface QuannanxianSaleMapper {

    List<QuannanxianSale> getSaleRatio();

    QuannanxianSale getSaleSin(String name);

    int updateSale(QuannanxianSale quannanxianSale);

    int insertSale(QuannanxianSale quannanxianSale);

    int deleteSale(String name);
}




