package com.sky.mapper;


import com.sky.entity.Slaughterhouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author 谭晋曦
* @description 针对表【slaughterhouse】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.Slaughterhouse
*/

@Mapper
public interface SlaughterhouseMapper {


    Slaughterhouse getTotalByDate(String formattedDate);



    List<Map<String, Object>> getTotalByDays(@Param("dates") List<String> dates);

    List<Slaughterhouse> getTotalByMonths(List<Date> dates);

    int updateByDate(Slaughterhouse slaughterhouse);


    int existsByTime(Date time);
    int insert(Slaughterhouse slaughterhouse);
    int deleteByDate(String date);



//    List<Slaughterhouse> getTotalByMonths(@Param("dates") List<Date> dates);
}




