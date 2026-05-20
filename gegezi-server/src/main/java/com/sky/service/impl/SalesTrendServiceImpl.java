package com.sky.service.impl;


import com.sky.dto.MonthlySalesTrendDTO;
import com.sky.entity.SalesStatus;
import com.sky.entity.SalesTrend;
import com.sky.mapper.SalesTrendMapper;
import com.sky.service.SalesTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* @author 谭晋曦
* @description 针对表【sales_trend】的数据库操作Service实现
* @createDate 2025-02-23 17:06:11
*/
@Service
public class SalesTrendServiceImpl
    implements SalesTrendService {
    @Autowired
    private SalesTrendMapper mapper;

    @Override
    public MonthlySalesTrendDTO getSaleTrend(String name) {
        final int monthsCount = 12;
        ZoneId zone = ZoneId.of("GMT+8");

        // 生成月份范围（当前月往前推11个月）
        YearMonth currentYearMonth = YearMonth.now(zone);
        List<YearMonth> yearMonthList = new ArrayList<>();
        for (int i = monthsCount - 1; i >= 0; i--) {
            yearMonthList.add(currentYearMonth.minusMonths(i));
        }

        // 转换为数据库查询的时间范围
        Date startDate = convertToDate(yearMonthList.get(0).atDay(1), zone);
        Date endDate = convertToDate(yearMonthList.get(monthsCount - 1).atEndOfMonth().plusDays(1), zone);

        // 查询数据库获取已有数据
        List<SalesTrend> dbList = mapper.getSaleTrend(startDate, endDate,name);
        Map<String, SalesTrend> monthMap = dbList.stream()
                .collect(Collectors.toMap(SalesTrend::getTime, Function.identity()));

        // 构建完整结果集
        List<SalesTrend> result = new ArrayList<>();
        for (YearMonth ym : yearMonthList) {
            String monthKey = ym.toString();
            SalesTrend mt = monthMap.getOrDefault(monthKey, createEmptyMonthlyTotal(monthKey,name));
            result.add(mt);
        }
        for(SalesTrend tmp:result){
            tmp.setTime(tmp.getTime().split("-")[1]);
        }

        MonthlySalesTrendDTO dto=new MonthlySalesTrendDTO();
        dto.setName(name+"12个月销量");
        ArrayList<String> month=new SalesStatusServiceImpl().getMonth();
        Integer[] price=new Integer[12];
        int flag=0;
        for(int i=0;i<month.size();i++){
            flag=0;
            int m=i+1;
            for(SalesTrend tmp:result){
                if(Integer.parseInt(tmp.getTime())==i){
                    flag=1;
                    price[i]=tmp.getPrice();
                    break;
                }
            }
            if(flag==0){
                price[i]=0;
            }
        }
        dto.setMonth(month);
        dto.setPrice(price);
        return dto;
    }

    @Override
    public boolean updateSaleTrend(SalesTrend salesTrend) {
        int rows=mapper.updateSaleTrend(salesTrend);
        return rows>0;
    }

    @Override
    public boolean insertSaleTrend(SalesTrend salesTrend) {
        SalesTrend s=mapper.getSaleStatusSin(salesTrend.getName(),salesTrend.getTime());
        if(s!=null){
            return mapper.updateSaleTrend(salesTrend)>0;
        }
        int rows=mapper.insertSaleTrend(salesTrend);
        return rows>0;
    }

    @Override
    public boolean deleteSaleTrend(String time, String name) {
        return  mapper.deleteSaleTrend(time,name) > 0;

    }

    private SalesTrend createEmptyMonthlyTotal(String month,String name) {
        SalesTrend mt = new SalesTrend();
        mt.setTime(month);
        mt.setSale(0);
        mt.setPrice(0);
        mt.setName(name);
        return mt;
    }
    private Date convertToDate(LocalDate date, ZoneId zone) {
        return Date.from(date.atStartOfDay(zone).toInstant());
    }
}




