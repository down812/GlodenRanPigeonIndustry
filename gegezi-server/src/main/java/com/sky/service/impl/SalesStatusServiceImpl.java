package com.sky.service.impl;


import com.sky.dto.SalesStatusDTO;
import com.sky.entity.SalesStatus;
import com.sky.mapper.SalesStatusMapper;
import com.sky.service.SalesStatusService;
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
* @description 针对表【sales_status】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class SalesStatusServiceImpl
    implements SalesStatusService {

    @Autowired
    private SalesStatusMapper mapper;

    @Override
    public SalesStatusDTO getSaleStatus(String name) {
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
        List<SalesStatus> dbList = mapper.getSaleStatus(startDate, endDate,name);
        Map<String, SalesStatus> monthMap = dbList.stream()
                .collect(Collectors.toMap(SalesStatus::getTime, Function.identity()));

        // 构建完整结果集
        List<SalesStatus> result = new ArrayList<>();
        for (YearMonth ym : yearMonthList) {
            String monthKey = ym.toString();
            SalesStatus mt = monthMap.getOrDefault(monthKey, createEmptyMonthlyTotal(monthKey,name));
            result.add(mt);
        }
        for(SalesStatus tmp:result){
            tmp.setTime(tmp.getTime().split("-")[1]);
        }

        SalesStatusDTO dto=new SalesStatusDTO();
        dto.setName(name+"月销量");
        ArrayList<String> month=getMonth();
        Integer[] money=new Integer[12];
        int flag=0;
        for(int i=0;i<month.size();i++){
            flag=0;
            int m=i+1;
            for(SalesStatus tmp:result){
                if(Integer.parseInt(tmp.getTime())==m){
                    flag=1;
                    money[i]=tmp.getMoney();
                    break;
                }
            }
            if(flag==0){
                money[i]=0;
            }
        }
        dto.setMonth(month);
        dto.setMoney(money);
        return dto;
    }

    @Override
    public boolean updateSaleStatus(SalesStatus salesStatus) {
        int rows=mapper.updateSaleStatus(salesStatus);
        return rows>0;
    }

    @Override
    public boolean insertSaleStatus(SalesStatus salesStatus) {
        SalesStatus s= mapper.getSaleStatusByTime(salesStatus.getTime(),salesStatus.getName());
        if(s!=null){
            return mapper.updateSaleStatus(salesStatus)>0;
        }
        int rows=mapper.insertSaleStatus(salesStatus);
        return rows>0;
    }

    @Override
    public boolean deleteSaleStatus(String time, String name) {
        return mapper.deleteSaleStatus(time,name)>0;
    }


    private SalesStatus createEmptyMonthlyTotal(String month,String kind) {
        SalesStatus mt = new SalesStatus();
        mt.setTime(month);
        mt.setMoney(0);
        mt.setName(kind);
        return mt;
    }

    private Date convertToDate(LocalDate date, ZoneId zone) {
        return Date.from(date.atStartOfDay(zone).toInstant());
    }

    public ArrayList<String> getMonth(){
        ArrayList<String> month=new ArrayList<>();
        month.add("一月");
        month.add("二月");
        month.add("三月");
        month.add("四月");
        month.add("五月");
        month.add("六月");
        month.add("七月");
        month.add("八月");
        month.add("九月");
        month.add("十月");
        month.add("十一月");
        month.add("十二月");
        return month;
    }
}




