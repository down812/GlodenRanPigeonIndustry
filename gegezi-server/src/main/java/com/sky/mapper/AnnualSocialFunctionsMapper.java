package com.sky.mapper;

import com.sky.entity.AnnualSocialFunctions;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【annual_social_functions】的数据库操作Mapper
* @createDate 2025-02-23 17:06:10
* @Entity com.niuma.entity.AnnualSocialFunctions
*/
@Mapper
public interface AnnualSocialFunctionsMapper {

    List<AnnualSocialFunctions> getSocialFun();

    int updateSocialFun(AnnualSocialFunctions annualSocialFunctions);
}




