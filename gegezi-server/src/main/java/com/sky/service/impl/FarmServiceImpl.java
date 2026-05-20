package com.sky.service.impl;


import com.sky.dto.YearFarmDTO;
import com.sky.entity.Farm;
import com.sky.mapper.FarmMapper;
import com.sky.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【farm】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class FarmServiceImpl
    implements FarmService {
@Autowired
private FarmMapper mapper;
    @Override
    public List<Farm> getIOSum() {
        List<Farm> list=mapper.getIOSum();
        return list;
    }

    @Override
    public List<YearFarmDTO> getIOYear() {
        List<Farm> list=mapper.getIOYear();
        List<YearFarmDTO> lists=new ArrayList<>();
        YearFarmDTO in=new YearFarmDTO();
        YearFarmDTO out=new YearFarmDTO();
        in.setName("种鸽、乳鸽每年存栏数");
        out.setName("种鸽、乳鸽每年出栏数");

        ArrayList<Integer> year=new ArrayList<>();
        int curYear=LocalDate.now().getYear();
        for(int i=curYear-4;i<=curYear;i++){
            year.add(i);
        }

         Integer[] seed_in=new Integer[5];
         Integer[] milk_in=new Integer[5];

        Integer[] seed_out=new Integer[5];
        Integer[] milk_out=new Integer[5];


        for(int i=0;i<year.size();i++){
            int y=year.get(i);
            for(Farm tmp:list){
                if(y==Integer.parseInt(tmp.getTime())){
                    if(tmp.getName().equals("种鸽")){
                        seed_in[i]=tmp.getInGove();
                        seed_out[i]=tmp.getOutGove();
                    }else if(tmp.getName().equals("乳鸽")){
                        milk_in[i]=tmp.getInGove();
                        milk_out[i]=tmp.getOutGove();
                    }
                }
            }
        }
        for(int i=0;i<5;i++){
            if(seed_in[i]==null){
                seed_in[i]=0;
            }
            if(seed_out[i]==null){
                seed_out[i]=0;
            }
            if(milk_in[i]==null){
                milk_in[i]=0;
            }
            if(milk_out[i]==null){
                milk_out[i]=0;
            }

        }



        in.setSeed(seed_in);
        in.setMilk(milk_in);
        in.setYear(year);

        out.setSeed(seed_out);
        out.setMilk(milk_out);
        out.setYear(year);

        lists.add(in);
        lists.add(out);
        return lists;
    }

    @Override
    public boolean updateFarm(Farm farm) {
        int rows=mapper.updateFarm(farm);
        return rows>0;
    }

    @Override
    public boolean insertFarm(Farm farm) {
        Farm f=mapper.getFarmSin(farm);
        if(f!=null){
            return mapper.updateFarm(farm)>0;
        }
        farm.setTime(farm.getTime()+"-01-01");
        int rows=mapper.insertFarm(farm);
        return rows>0;
    }

    @Override
    public boolean deleteFarm(String time, String name) {
        return mapper.deleteFarm(time,name)>0;
    }
}




