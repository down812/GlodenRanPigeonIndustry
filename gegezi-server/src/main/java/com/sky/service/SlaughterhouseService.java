package com.sky.service;

import com.sky.dto.SlaughterhouseDTO;
import com.sky.entity.Slaughterhouse;

/**
* @author 谭晋曦
* @description 针对表【slaughterhouse】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface SlaughterhouseService  {

    Slaughterhouse getTotal(String today);

    SlaughterhouseDTO getTotalByDays(int numberOfSelectingDay);

    SlaughterhouseDTO getTotalByMonths();



    boolean updateByDate(Slaughterhouse slaughterhouse);


    boolean addByDate(Slaughterhouse slaughterhouse);

    boolean deleteByDate(String date);
}
