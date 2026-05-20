package com.sky.service;

import com.sky.dto.PigeonCagesDTO;
import com.sky.entity.BaseEconomyTrend;
import com.sky.entity.IncubationStatus;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【incubation_status】的数据库操作Service
* @createDate 2025-02-23 17:06:11
*/
public interface IncubationStatusService  {
    /**
     * 查询鸽笼状态分布
     * @return
     */
    List<PigeonCagesDTO> getPigeonCages();
    boolean  updateIncubation(IncubationStatus incubationStatus);

}
