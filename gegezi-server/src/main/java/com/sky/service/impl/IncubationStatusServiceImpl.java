package com.sky.service.impl;


import com.sky.dto.PigeonCagesDTO;
import com.sky.entity.IncubationStatus;
import com.sky.mapper.IncubationStatusMapper;
import com.sky.service.IncubationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【incubation_status】的数据库操作Service实现
* @createDate 2025-02-23 17:06:11
*/
@Service
public class IncubationStatusServiceImpl implements IncubationStatusService {

    @Autowired
    IncubationStatusMapper incubationStatusMapper;


    /**
     * 查询鸽笼状态分布
     */
    @Override
    public List<PigeonCagesDTO> getPigeonCages(){
        return incubationStatusMapper.getPigeonCages();
    }
    @Override
    public   boolean updateIncubation(IncubationStatus incubationStatus){
        return incubationStatusMapper.updateIncubation(incubationStatus)>0;
    }

}




