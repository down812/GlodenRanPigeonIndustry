package com.sky.service;


import com.sky.dto.YearFarmDTO;
import com.sky.entity.Farm;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【farm】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface FarmService{

    List<Farm> getIOSum();

    List<YearFarmDTO> getIOYear();

    boolean updateFarm(Farm farm);

    boolean insertFarm(Farm farm);

    boolean deleteFarm(String time, String name);
}
