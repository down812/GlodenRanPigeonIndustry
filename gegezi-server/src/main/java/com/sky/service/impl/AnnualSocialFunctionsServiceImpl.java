package com.sky.service.impl;


import com.sky.dto.AnnualSocialFunctionsDTO;
import com.sky.entity.AnnualSocialFunctions;
import com.sky.mapper.AnnualSocialFunctionsMapper;
import com.sky.service.AnnualSocialFunctionsService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【annual_social_functions】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class AnnualSocialFunctionsServiceImpl
    implements AnnualSocialFunctionsService {
@Autowired
private AnnualSocialFunctionsMapper mapper;
    @Override
    public AnnualSocialFunctionsDTO getSocialFun() {
        List<AnnualSocialFunctions> entity = mapper.getSocialFun();
        AnnualSocialFunctionsDTO dto=new AnnualSocialFunctionsDTO();
        ArrayList<String> name=new ArrayList<>();
        ArrayList<Integer> data=new ArrayList<>();
        for(AnnualSocialFunctions tmp:entity){
            name.add(tmp.getName());
            data.add(tmp.getNumbers());
        }
        dto.setName(name);dto.setData(data);
        return dto;
    }

    @Override
    public boolean updateSocialFun(AnnualSocialFunctions annualSocialFunctions) {
        int rows=  mapper.updateSocialFun(annualSocialFunctions);
        return rows>0;
    }
}




