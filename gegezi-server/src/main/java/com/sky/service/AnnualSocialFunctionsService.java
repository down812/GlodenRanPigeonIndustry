package com.sky.service;


import com.sky.dto.AnnualSocialFunctionsDTO;
import com.sky.entity.AnnualSocialFunctions;

/**
* @author 谭晋曦
* @description 针对表【annual_social_functions】的数据库操作Service
* @createDate 2025-02-23 17:06:10
*/
public interface AnnualSocialFunctionsService {

    AnnualSocialFunctionsDTO getSocialFun();

    boolean updateSocialFun(AnnualSocialFunctions annualSocialFunctions);
}
