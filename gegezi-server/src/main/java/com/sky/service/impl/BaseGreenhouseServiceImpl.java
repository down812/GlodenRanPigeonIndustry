package com.sky.service.impl;


import com.sky.dto.AgeDTO;
import com.sky.dto.BaseGreenhouseDTO;
import com.sky.dto.QuarterDTO;
import com.sky.entity.BaseDoveDestination;
import com.sky.entity.BaseGreenhouse;
import com.sky.entity.IncubationRecord;
import com.sky.entity.PigeonAgeSteps;
import com.sky.mapper.BaseGreenhouseMapper;
import com.sky.service.BaseGreenhouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【base_personnel_distribution】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Slf4j
@Service
public class BaseGreenhouseServiceImpl implements BaseGreenhouseService {
    @Autowired
    BaseGreenhouseMapper baseGreenhouseMapper;

    @Override
   public List<BaseGreenhouseDTO> getBaseGreenhouse(){
       return baseGreenhouseMapper.getBaseGreenhouse();
   }

    @Override
    public boolean updateBase(BaseGreenhouse baseGreenhouse) {
        return baseGreenhouseMapper.updateBase(baseGreenhouse)>0;
    }


    /**
     * 修改孵化机记录分步
     */
    @Override
    public boolean   updateRecord(IncubationRecord incubationRecord) {
        return baseGreenhouseMapper.updateRecord(incubationRecord) > 0;
    }




   @Override
    public List<QuarterDTO> getQuarter(){
        return baseGreenhouseMapper.getQuarter();
   }




   @Override
   public AgeDTO getAger(){
        log.info("getAger:{}",baseGreenhouseMapper.getAger());
        return  baseGreenhouseMapper.getAger();
   }

    /**
     * 修改鸽龄分步
     */
    @Override
    public boolean updateAger(PigeonAgeSteps pigeonAgeSteps) {
        return baseGreenhouseMapper.updateAger(pigeonAgeSteps) > 0;
    }


}




