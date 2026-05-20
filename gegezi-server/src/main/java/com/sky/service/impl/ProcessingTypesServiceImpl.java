package com.sky.service.impl;


import com.sky.dto.ProcessingTypesDTO;
import com.sky.dto.SlaughterhouseDTO;
import com.sky.entity.ProcessingTypes;
import com.sky.entity.Slaughterhouse;
import com.sky.mapper.ProcessingTypesMapper;
import com.sky.service.ProcessingTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
* @author 谭晋曦
* @description 针对表【processing_types】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class ProcessingTypesServiceImpl implements ProcessingTypesService {

    @Autowired
    private ProcessingTypesMapper processingTypeMapper;

    @Override
    public ProcessingTypesDTO getTotalByMonths() {
        Calendar calendar = Calendar.getInstance();
        // 获取近12个月的日期范围
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.MONTH, -1);
        }
        Collections.reverse(dates);

        List<ProcessingTypes> processingTypes = processingTypeMapper.getTotalByMonths(dates);

        // 初始化结果
        ProcessingTypesDTO dto = new ProcessingTypesDTO();
        List<String> timeData = new ArrayList<>();
        List<Integer> countData = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("MM月");
        Map<String, Integer> monthCountMap = new HashMap<>();
        for (ProcessingTypes processingType : processingTypes) {
            String month = sdf.format(processingType.getTime());
            monthCountMap.put(month, monthCountMap.getOrDefault(month, 0) + processingType.getNumbers());
        }

        // 填充时间和数量数据
        for (Date date : dates) {
            String month = sdf.format(date);
            timeData.add(month);
            countData.add(monthCountMap.getOrDefault(month, 0));
        }

        dto.setTimeData(timeData);
        dto.setCountData(countData);
        return dto;
    }

    @Override
    public ProcessingTypes getTotal(String today) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 统一日期格式处理
            Date parsedDate = dateFormat.parse(today);
            String formattedDate = dateFormat.format(parsedDate);

            // 调用Mapper获取汇总数据
            return processingTypeMapper.getDailySummary(formattedDate);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public ProcessingTypesDTO getTotalByDays(int numberOfSelectingDay) {

            // 获取当前日期
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            // 存储 7 天的日期
            List<Date> dates = new ArrayList<>();
            for (int i = 0; i < numberOfSelectingDay; i++) {
                dates.add(calendar.getTime());
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            }
            Collections.reverse(dates); // 反转列表，使其按时间顺序排列

            // 调用 Mapper 方法获取数据
            List<ProcessingTypes> processingTypes = processingTypeMapper.getTotalByDays(dates);

            // 初始化返回结果
            ProcessingTypesDTO dto = new ProcessingTypesDTO();
            List<String> timeData = new ArrayList<>();
            List<Integer> countData = new ArrayList<>();

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.CHINA);
            for (Date date : dates) {
                timeData.add(sdf.format(date));
                int count = 0;
                for (ProcessingTypes processingType : processingTypes) {
                    if (isSameDay(processingType.getTime(), date)) {
                        count = processingType.getNumbers();
                        break;
                    }
                }
                countData.add(count);
            }

            dto.setTimeData(timeData);
            dto.setCountData(countData);
            return dto;
    }

    @Override
    public boolean updateProcessingTypes(ProcessingTypes processingTypes) {
        int rows = processingTypeMapper.updateProcessingTypes(processingTypes);
        return rows > 0;
    }

    @Override
    public boolean addProcessingTypes(ProcessingTypes processingTypes) {
        // 检查日期和名字是否已存在
        if (processingTypeMapper.existsByTimeAndName(processingTypes.getTime(), processingTypes.getName()) > 0) {
            return false;
        }
        return processingTypeMapper.insert(processingTypes) > 0;
    }

    @Override
    public boolean deleteProcessingTypes(ProcessingTypes processingTypes) {
        return processingTypeMapper.deleteByTimeAndName(processingTypes.getTime(), processingTypes.getName()) > 0;

    }


    private boolean isSameDay(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(date1);
    Calendar cal2 = Calendar.getInstance();
    cal2.setTime(date2);
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
}


}




