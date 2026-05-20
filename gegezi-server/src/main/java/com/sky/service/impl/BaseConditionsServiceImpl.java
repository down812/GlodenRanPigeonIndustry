package com.sky.service.impl;

import com.sky.dto.*;
import com.sky.entity.BaseConditions;
import com.sky.entity.BaseEconomyTrend;
import com.sky.entity.BasePersonnelDistribution;
import com.sky.entity.ProcessingTypes;
import com.sky.mapper.BaseConditionsMapper;
import com.sky.service.BaseConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



/**
* @author 谭晋曦
* @description 针对表【base_conditions】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class BaseConditionsServiceImpl implements BaseConditionsService {
    @Autowired
    BaseConditionsMapper baseConditionsMapper;


    /**
     * 查询基地各种情况的数量
     * @return
     */
    @Override
    public BaseConditionsDTO selectBaseData() {
        return baseConditionsMapper.selectBaseData();
    }

    @Override
    public boolean updateMessage(BaseConditions baseConditions){
        return baseConditionsMapper.updateMessage(baseConditions) > 0;
    }
    @Override
    public boolean updateDeep(ProcessingTypes processingTypes){
        return baseConditionsMapper.updateDeep(processingTypes) > 0;
    }
    @Override
    public boolean updatePerson(BasePersonnelDistribution BasePersonnelDistribution){
        return baseConditionsMapper.updatePerson(BasePersonnelDistribution) > 0;
    }



    /**
     * 查询基地种鸽、乳鸽养殖规模（按年份）
     */
    @Override
    public List<FarmProductionSummaryDTO> getFarmProductionSummary() {
        // 直接返回Mapper查询结果，因为数据已经是List<Integer>类型
        return baseConditionsMapper.selectFarmProductionSummary();
    }

    /**
     * 获取基地经济产值走势
     * @return
     */
    @Override
   public List<BaseEconomyDTO> getAllByMoney(){
        return baseConditionsMapper.getAllByMoney();
    }

    @Override
    public boolean updateMoney(BaseEconomyTrend baseEconomyTrend){
        return baseConditionsMapper.updateMoney(baseEconomyTrend) > 0;
    }

    @Override
    public List<BasePersonDeepDTO> getPersonDeep(){
        return baseConditionsMapper.getPersonDeep();
    }
}




