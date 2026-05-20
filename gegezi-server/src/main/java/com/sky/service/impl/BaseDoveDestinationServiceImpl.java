package com.sky.service.impl;


import com.sky.dto.BaseDoveDestinationDTO;
import com.sky.entity.BaseDoveDestination;
import com.sky.mapper.BaseConditionsMapper;
import com.sky.mapper.BaseDoveDestinationMapper;
import com.sky.service.BaseDoveDestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【base_dove_destination】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class BaseDoveDestinationServiceImpl implements BaseDoveDestinationService {

    @Autowired
    BaseDoveDestinationMapper baseDoveDestinationMapper;

    /**
     * 查询基地乳鸽去向
     *
     * @return
     */
    @Override
    public List<BaseDoveDestinationDTO> getDestination() {
        return baseDoveDestinationMapper.getDestination();
    }


    /**
     * 修改基地乳鸽去向的相关数据
     */
    @Override
    public boolean updateNumbers(BaseDoveDestination destination) {
        return baseDoveDestinationMapper.updateNumbersByName(destination) > 0;
    }



//    /**
//     * 修改基地乳鸽去向的相关数据
//     */
//    @Override
//    public boolean updateDestination(String oldName, String newName, Integer numbers) {
//        return baseDoveDestinationMapper.updateNameAndNumber(oldName, newName, numbers) > 0;
//    }
//
//    @Override
//    public boolean addDestination(BaseDoveDestination destination) {
//        return baseDoveDestinationMapper.insertDestination(destination) > 0;
//    }
//
//    @Override
//    public boolean deleteDestination(String name) {
//        return baseDoveDestinationMapper.deleteByName(name) > 0;
//    }


}




