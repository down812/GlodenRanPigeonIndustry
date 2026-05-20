package com.sky.mapper;


import com.sky.entity.Farm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【farm】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.Farm
*/
@Mapper
public interface FarmMapper {

    List<Farm> getIOSum();

    List<Farm> getIOYear();

    int updateFarm(Farm farm);

    Farm getFarmSin(Farm farm);

    int insertFarm(Farm farm);

    int deleteFarm(String time, String name);
}




