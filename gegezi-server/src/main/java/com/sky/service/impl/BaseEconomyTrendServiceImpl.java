package com.sky.service.impl;


import com.sky.dto.BaseEconomyTrendDTO;
import com.sky.entity.BaseEconomyTrend;
import com.sky.mapper.BaseEconomyTrendMapper;
import com.sky.service.BaseEconomyTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BaseEconomyTrendServiceImpl implements BaseEconomyTrendService {
    @Autowired
    private BaseEconomyTrendMapper mapper;
    @Override
    public BaseEconomyTrendDTO getBaseEconomyTrend() {
        List<BaseEconomyTrend> list=mapper.getBaseEconomyTrend();
        BaseEconomyTrendDTO dto=new BaseEconomyTrendDTO();
        ArrayList<Integer> year=new ArrayList<>();
        int curYear= LocalDate.now().getYear();
        for(int i=curYear-3;i<=curYear;i++){
            year.add(i);
        }
         ArrayList<Integer> money=new ArrayList<>();
        int flag=0;
        for(int i=0;i<year.size();i++){
            flag=0;
            int y=year.get(i);
            for(BaseEconomyTrend tmp:list){
                if(y==Integer.parseInt(tmp.getTime())){
                    flag=1;
                    money.add(i,tmp.getMoney());
                    break;
                }
            }
            if(flag==0){
                money.add(i,0);
            }
        }
        dto.setYear(year);
        dto.setMoney(money);
        return dto;
    }

    @Override
    public boolean updateBaseEconomyTrend(BaseEconomyTrend baseEconomyTrend) {
        int rows=mapper.updateBaseEconomyTrend(baseEconomyTrend);
        return rows>0;
    }

    @Override
    public boolean insertBaseEconomyTrend(BaseEconomyTrend baseEconomyTrend) {
        return mapper.insertBaseEconomyTrend(baseEconomyTrend)>0;
    }

    @Override
    public boolean deleteBaseEconomyTrend(String time) {
        return mapper.deleteBaseEconomyTrend(time)>0;
    }
}
