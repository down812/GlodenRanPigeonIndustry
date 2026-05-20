package com.sky.service;


import com.sky.dto.*;
import com.sky.entity.BaseConditions;
import com.sky.entity.BaseEconomyTrend;
import com.sky.entity.BasePersonnelDistribution;
import com.sky.entity.ProcessingTypes;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【base_conditions】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface BaseConditionsService {
    BaseConditionsDTO selectBaseData();
    boolean updateMessage(BaseConditions baseConditions);
    boolean updateDeep(ProcessingTypes processingTypes);
    boolean updatePerson(BasePersonnelDistribution BasePersonnelDistribution);

    List<FarmProductionSummaryDTO> getFarmProductionSummary();


    List<BaseEconomyDTO> getAllByMoney();
    boolean  updateMoney(BaseEconomyTrend baseEconomyTrend);

    List<BasePersonDeepDTO> getPersonDeep();
}
