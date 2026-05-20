package com.sky.service;


import com.sky.dto.ProcessingVarietiesDTO2;
import com.sky.entity.ProcessingVarieties;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【processing_varieties】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface ProcessingVarietiesService  {

    List<ProcessingVarieties> getVarieties();


    boolean updateVarieties(ProcessingVarietiesDTO2 dto);
}
