package com.sky.service;

import com.sky.dto.ProcessingTypesDTO;
import com.sky.entity.ProcessingTypes;

/**
* @author 谭晋曦
* @description 针对表【processing_types】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface ProcessingTypesService  {

    ProcessingTypesDTO getTotalByMonths();

    ProcessingTypes getTotal(String today);

    ProcessingTypesDTO getTotalByDays(int numberOfSelectingDay);

    boolean updateProcessingTypes(ProcessingTypes processingTypes);

    boolean addProcessingTypes(ProcessingTypes processingTypes);

    boolean deleteProcessingTypes(ProcessingTypes processingTypes);
}
