package com.sky.mapper;


import com.sky.dto.ProcessingVarietiesDTO;
import com.sky.entity.ProcessingVarieties;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【processing_varieties】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.ProcessingVarieties
*/
@Mapper
public interface ProcessingVarietiesMapper {

    List<ProcessingVarieties> getVarieties();



    int updateProcessingVarieties(ProcessingVarieties processingVarieties);
}




