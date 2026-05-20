package com.sky.service.impl;


import com.sky.dto.SlaughterhouseDTO;
import com.sky.entity.Slaughterhouse;
import com.sky.mapper.SlaughterhouseMapper;
import com.sky.service.SlaughterhouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* @author 谭晋曦
* @description 针对表【slaughterhouse】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class SlaughterhouseServiceImpl implements SlaughterhouseService {

    @Autowired
    private SlaughterhouseMapper slaughterhouseMapper;

    @Override
    public Slaughterhouse getTotal(String today) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 将输入的日期字符串格式化为"yyyy-MM-dd"
            Date parsedDate = dateFormat.parse(today);
            String formattedDate = dateFormat.format(parsedDate);

            // 使用格式化后的日期查询数据
            Slaughterhouse total = slaughterhouseMapper.getTotalByDate(formattedDate);
            return total; // 如果没有找到数据，mapper层应返回null，此处直接返回即可
        } catch (ParseException e) {
            // 处理日期解析异常
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SlaughterhouseDTO getTotalByDays(int numberOfSelectingDay) {
        // 生成近numberOfDays天（包含今天）的日期列表
        LocalDate today = LocalDate.now();
        List<LocalDate> dateList = IntStream.range(0, 7)
                .mapToObj(i -> today.minusDays(i))
                .collect(Collectors.toList());

        // 反转日期列表，使其从最早到最近
        Collections.reverse(dateList);

        // 转换为数据库查询需要的字符串格式（yyyy-MM-dd）
        List<String> dateStrList = dateList.stream()
                .map(date -> date.format(DateTimeFormatter.ISO_LOCAL_DATE))
                .collect(Collectors.toList());

        // 查询数据库获取有数据的日期统计
        List<Map<String, Object>> dbResults = slaughterhouseMapper.getTotalByDays(dateStrList);

        Map<LocalDate, Integer> dateCountMap = dbResults.stream()
                .collect(Collectors.toMap(
                        result -> {
                            Object dateObj = result.get("date");
                            if (dateObj instanceof java.sql.Date) {
                                return ((java.sql.Date) dateObj).toLocalDate();
                            } else if (dateObj instanceof String) {
                                return LocalDate.parse((String) dateObj);
                            }
                            throw new IllegalArgumentException("Unsupported date type: " + dateObj.getClass());
                        },
                        result -> ((Number) result.get("total")).intValue()
                ));

        // 填充结果集（日期转星期名称，并处理缺失数据）
        SlaughterhouseDTO dto = new SlaughterhouseDTO();
        List<String> timeData = new ArrayList<>();
        List<Integer> countData = new ArrayList<>();

        dateList.forEach(date -> {
            timeData.add(getChineseWeekday(date));
            countData.add(dateCountMap.getOrDefault(date, 0));
        });

        dto.setTimeData(timeData);
        dto.setCountData(countData);
        return dto;
    }
    // 将日期转换为中文星期名称
    private String getChineseWeekday(LocalDate date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int dayOfWeek = date.getDayOfWeek().getValue() % 7;
        return weekDays[dayOfWeek];
    }

    // 修改isSameDay方法
    private boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }



    public SlaughterhouseDTO getTotalByMonths() {
        // 获取当前日期
        Calendar calendar = Calendar.getInstance();
        // 获取近12个月的日期范围
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            dates.add(calendar.getTime());
            calendar.add(Calendar.MONTH, -1);
        }
        Collections.reverse(dates);

        // 从数据库查询近12个月的屠宰数据
        List<Slaughterhouse> slaughterhouses = slaughterhouseMapper.getTotalByMonths(dates);

        // 初始化结果
        SlaughterhouseDTO dto = new SlaughterhouseDTO();
        List<String> timeData = new ArrayList<>();
        List<Integer> countData = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("MM月");
        Map<String, Integer> monthCountMap = new HashMap<>();
        for (Slaughterhouse slaughterhouse : slaughterhouses) {
            String month = sdf.format(slaughterhouse.getTime());
            monthCountMap.put(month, monthCountMap.getOrDefault(month, 0) + slaughterhouse.getKillNumber());
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
    public boolean updateByDate(Slaughterhouse slaughterhouse) {
        int rows = slaughterhouseMapper.updateByDate(slaughterhouse);
        return rows > 0;
    }

    @Override
    public boolean addByDate(Slaughterhouse slaughterhouse) {
        Date date = slaughterhouse.getTime();
        LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Date compareDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if (slaughterhouseMapper.existsByTime(slaughterhouse.getTime()) > 0) {
            return false;
        }
        int rows = slaughterhouseMapper.insert(slaughterhouse);
        return rows > 0;
    }

    @Override
    public boolean deleteByDate(String date) {
        int rows = slaughterhouseMapper.deleteByDate(date);
        return rows > 0;
    }


}





