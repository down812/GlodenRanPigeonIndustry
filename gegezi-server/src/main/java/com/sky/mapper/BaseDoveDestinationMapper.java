package com.sky.mapper;


import com.sky.dto.BaseDoveDestinationDTO;
import com.sky.entity.BaseDoveDestination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【base_dove_destination】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.BaseDoveDestination
*/
@Mapper
public interface BaseDoveDestinationMapper{

    /**
     * 查询基地乳鸽去向
     */
    List<BaseDoveDestinationDTO> getDestination();

    /**
     * 修改基地乳鸽去向的相关数据
     */
    int updateNumbersByName(BaseDoveDestination destination);


//    /**
//     * 修改基地乳鸽去向的相关数据
//     */
//    int updateNameAndNumber(
//            @Param("oldName") String oldName,
//            @Param("newName") String newName,
//            @Param("numbers") Integer numbers);
//    /**
//     * 新增基地乳鸽去向数据
//     * @param destination
//     * @return
//     */
//    int insertDestination(BaseDoveDestination destination);
//
//    /**
//     * 根据名字来删除
//     * @param name
//     * @return
//     */
//    int deleteByName(@Param("name") String name);


}




