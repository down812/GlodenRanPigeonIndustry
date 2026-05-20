package com.sky.service;


import com.sky.dto.BaseDoveDestinationDTO;
import com.sky.entity.BaseDoveDestination;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【base_dove_destination】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface BaseDoveDestinationService  {
    /**
     * 查询基地乳鸽去向(饼图)
     * @return
     */
    List<BaseDoveDestinationDTO> getDestination();


    /**
      * 修改基地乳鸽去向的相关数据
      */
    boolean updateNumbers(BaseDoveDestination destination);

//    /**
//     * 修改基地乳鸽去向的相关数据
//     */
//    boolean updateDestination(String oldName, String newName, Integer numbers);
//
//    /**
//     * 新增乳鸽去向种类
//     * @param destination
//     * @return
//     */
//    boolean addDestination(BaseDoveDestination destination);
//
//    /**
//     * 删除某个乳鸽去向
//     * @param name
//     * @return
//     */
//    boolean deleteDestination(String name);


}
