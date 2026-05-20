package com.sky.mapper;


import com.sky.entity.ProcessingTypes;
import org.apache.ibatis.annotations.Mapper;
import java.util.Date;
import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【processing_types】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.ProcessingTypes
*/

@Mapper
public interface ProcessingTypesMapper {
    List<ProcessingTypes> getTotalByLast7Days(List<Date> last7Days);

    ProcessingTypes getTotalByDate(String formattedDate);

   List<ProcessingTypes> getTotalByDays(List<Date> dates);

    int updateProcessingTypes(ProcessingTypes processingTypes);

    List<ProcessingTypes> getTotalByMonths(List<Date> dates);

    int existsByTimeAndName(Date time, String name);

    int insert(ProcessingTypes processingTypes);

    int deleteByTimeAndName(Date time, String name);

    ProcessingTypes getDailySummary(String formattedDate);
}




