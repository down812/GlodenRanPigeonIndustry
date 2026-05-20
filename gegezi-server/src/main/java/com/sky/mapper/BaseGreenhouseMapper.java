package com.sky.mapper;

import com.sky.dto.AgeDTO;
import com.sky.dto.BaseGreenhouseDTO;
import com.sky.dto.QuarterDTO;
import com.sky.entity.BaseGreenhouse;
import com.sky.entity.IncubationRecord;
import com.sky.entity.PigeonAgeSteps;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseGreenhouseMapper {

    /**
     * 查询基地大棚生产信息
     * @return
     */
    List<BaseGreenhouseDTO> getBaseGreenhouse();

    /**
     * 修改基地大棚生产信息
     * @param baseGreenhouse
     * @return
     */
    int updateBase(BaseGreenhouse baseGreenhouse);

    /**
     * 修改孵化机记录分布
     */
    int updateRecord(IncubationRecord incubationRecord);

    /**
     * 按季度查询乳鸽出栏数、鸽蛋异常
     */
    List<QuarterDTO> getQuarter();

    /**
     * 查询鸽龄分步
     */
    AgeDTO getAger();

    /**
     * 更改鸽龄分布
     * @param pigeonAgeSteps
     * @return
     */
    int updateAger(PigeonAgeSteps pigeonAgeSteps);
}
