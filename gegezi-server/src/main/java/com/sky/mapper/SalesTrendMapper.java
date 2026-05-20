package com.sky.mapper;


import com.sky.entity.SalesStatus;
import com.sky.entity.SalesTrend;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【sales_trend】的数据库操作Mapper
* @createDate 2025-02-23 17:06:11
* @Entity com.niuma.entity.SalesTrend
*/
@Mapper
public interface SalesTrendMapper  {

    List<SalesTrend> getSaleTrend(Date startDate, Date endDate, String name);

    SalesTrend getSaleStatusSin(String name,String time);

    int updateSaleTrend(SalesTrend salesTrend);

    int insertSaleTrend(SalesTrend salesTrend);

    int deleteSaleTrend(String time, String name);
}




