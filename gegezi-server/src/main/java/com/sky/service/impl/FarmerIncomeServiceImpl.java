package com.sky.service.impl;


import com.sky.dto.FarmerIncomeDTO;
import com.sky.entity.FarmerIncome;
import com.sky.mapper.FarmerIncomeMapper;
import com.sky.service.FarmerIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【farmer_income】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class FarmerIncomeServiceImpl
    implements FarmerIncomeService {

    @Autowired
    private FarmerIncomeMapper mapper;
    @Override
    public FarmerIncomeDTO getFarmerIncome(String name) {
        List<FarmerIncome> list=mapper.getFarmerIncome(name);
        FarmerIncomeDTO dto=new FarmerIncomeDTO();
        ArrayList<Integer> year=new ArrayList<>();
        int curYear= LocalDate.now().getYear();

        for(int i=curYear-4;i<=curYear;i++){
            year.add(i);
        }//把年份写入year中
        BigDecimal[] income=new BigDecimal[5];
        dto.setName(name);
        int flag=0;
        for(int i=0;i<year.size();i++){
            flag=0;
            int y=year.get(i);//获取年份
            for(FarmerIncome tmp:list){
                if(y==Integer.parseInt(tmp.getTime())){
                    flag=1;
                    income[i]=tmp.getNumbers();
                    break;
                }
            }
            if(flag==0){
                income[i]=BigDecimal.valueOf(0);
            }
        }

        dto.setYear(year);
        dto.setIncome(income);
        return dto;
    }

    @Override
    public boolean updateFarmerIncome(FarmerIncome farmerIncome) {
        int rows = mapper.updateFarmerIncome(farmerIncome);
        return rows>0;
    }

    @Override
    public boolean insertFarmerIncome(FarmerIncome farmerIncome) {
        FarmerIncome rows=mapper.getFarmerIncomeSin(farmerIncome);
        if(rows!=null){
            return mapper.updateFarmerIncome(farmerIncome)>0;
        }//如果已经存在则直接在原有数据上更新
        String time = farmerIncome.getTime();
        farmerIncome.setTime(time+"-01-01");
        return mapper.insertFarmerIncome(farmerIncome)>0;
    }

    @Override
    public boolean deleteFarmerIncome(String name, String time) {
        return mapper.deleteFarmerIncome(name,time)>0;
    }
}




