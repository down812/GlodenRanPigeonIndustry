package com.sky.service;


import com.sky.dto.SalesStatusDTO;
import com.sky.entity.SalesStatus;

/**
* @author 谭晋曦
* @description 针对表【sales_status】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface SalesStatusService {

    SalesStatusDTO getSaleStatus(String name);

    boolean updateSaleStatus(SalesStatus salesStatus);

    boolean insertSaleStatus(SalesStatus salesStatus);

    boolean deleteSaleStatus(String time, String name);
}
