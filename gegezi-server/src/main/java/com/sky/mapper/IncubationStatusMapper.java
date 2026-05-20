package com.sky.mapper;


import com.sky.dto.PigeonCagesDTO;
import com.sky.entity.IncubationStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【incubation_status】的数据库操作Mapper
* @createDate 2025-02-23 17:06:11
* @Entity com.niuma.entity.IncubationStatus
*/
@Mapper
public interface IncubationStatusMapper  {

    /**
     * 查询鸽笼状态分布
     * @return
     */
    List<PigeonCagesDTO> getPigeonCages();

    int updateIncubation(IncubationStatus incubationStatus);
}




