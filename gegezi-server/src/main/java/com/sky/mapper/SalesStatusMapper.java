package com.sky.mapper;


import com.sky.entity.SalesStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【sales_status】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.SalesStatus
*/
@Mapper
public interface SalesStatusMapper {

    List<SalesStatus> getSaleStatus(Date startDate, Date endDate, String name);

    SalesStatus getSaleStatusByTime(String time, String name);


    int updateSaleStatus(SalesStatus salesStatus);

    int insertSaleStatus(SalesStatus salesStatus);

    int deleteSaleStatus(String time, String name);


}




