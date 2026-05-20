package com.sky.mapper;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.sky.dto.BaseEconomyDTO;
import com.sky.dto.BasePersonDeepDTO;
import com.sky.dto.*;
import com.sky.entity.BaseConditions;
import com.sky.entity.BaseEconomyTrend;
import com.sky.entity.BasePersonnelDistribution;
import com.sky.entity.ProcessingTypes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【base_conditions】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.BaseConditions
*/
@Mapper
public interface BaseConditionsMapper {
    /**
     * 查询基地各种情况的数量
     * @return
     */
    BaseConditionsDTO selectBaseData();

    /**
     * 根据名字来修改某个数据
     */
    int updateMessage(BaseConditions baseConditions);

    /**
     * 查询基地种鸽、乳鸽养殖规模（年份）
     */
    List<FarmProductionSummaryDTO> selectFarmProductionSummary();

    /**
     * 查询基地经济产值走势
     * @return
     */
    List<BaseEconomyDTO> getAllByMoney();


    /**
     * 查询深加工分类和基地人员分布
     */
    List<BasePersonDeepDTO> getPersonDeep();

    /**
     * 根据名字修改深加工产品分类
     */
    int updateDeep(ProcessingTypes processingTypes);

    /**
     * 根据名字修改基地人员分布
     */
    int updatePerson(BasePersonnelDistribution basePersonnelDistribution);

    /**
     * 根据time来修改那年的money
     * @param
     * @param
     * @return
     */
    int updateMoney(BaseEconomyTrend baseEconomyTrend);
}




