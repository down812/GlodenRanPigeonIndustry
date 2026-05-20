package com.sky.service;


import com.sky.dto.AgeDTO;
import com.sky.dto.BaseGreenhouseDTO;
import com.sky.dto.QuarterDTO;
import com.sky.entity.BaseGreenhouse;
import com.sky.entity.IncubationRecord;
import com.sky.entity.PigeonAgeSteps;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【base_personnel_distribution】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface BaseGreenhouseService {
    List<BaseGreenhouseDTO> getBaseGreenhouse();

    boolean   updateRecord(IncubationRecord incubationRecord);

    List<QuarterDTO> getQuarter();

    AgeDTO getAger();

    boolean updateAger(PigeonAgeSteps pigeonAgeSteps);

    boolean updateBase(BaseGreenhouse baseGreenhouse);
}
