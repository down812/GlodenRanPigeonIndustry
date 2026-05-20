package com.sky.controller.page7;

import com.sky.dto.AnnualSocialFunctionsDTO;
import com.sky.entity.AnnualSocialFunctions;
import com.sky.result.Result;
import com.sky.service.AnnualSocialFunctionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@Api(tags = "合作社数、养殖农户数、就业人数、种鸽数、饲料量、技术服务")
public class SocialFunController {
    @Autowired
    private AnnualSocialFunctionsService service;

    @ApiOperation("获取合作社数、养殖农户数、就业人数、种鸽数、饲料量、技术服务")
    @GetMapping("/social")
    public Result getSocialFun(){
        AnnualSocialFunctionsDTO dto=service.getSocialFun();
        return dto!=null?Result.success(dto).setName("获取合作社数、养殖农户数、就业人数、种鸽数、饲料量、技术服务")
                :Result.error("查询失败").setName("获取合作社数、养殖农户数、就业人数、种鸽数、饲料量、技术服务");
    }

    @ApiOperation("更新合作社数、养殖农户数、就业人数、种鸽数、饲料量、技术服务")
    @PutMapping("/social")
    public Result updateSocialFun(@RequestBody AnnualSocialFunctions annualSocialFunctions){
        boolean result=service.updateSocialFun(annualSocialFunctions);
        return result?Result.success().setName("更新合作社数、养殖农户数、就业人数、种鸽数、饲料量、技术服务")
                :Result.error("更新失败").setName("更新合作社数、养殖农户数、就业人数、种鸽数、饲料量、技术服务");
    }



}
