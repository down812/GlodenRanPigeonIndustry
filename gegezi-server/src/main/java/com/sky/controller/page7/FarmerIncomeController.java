package com.sky.controller.page7;

import com.sky.dto.FarmerIncomeDTO;
import com.sky.entity.FarmerIncome;
import com.sky.result.Result;
import com.sky.service.FarmerIncomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@Api(tags = "获取农户收入")
public class FarmerIncomeController {

    @Autowired(required = false)
    private FarmerIncomeService farmerIncomeService;

    @ApiOperation("获取农户每年人均和总收入")
    @GetMapping("/farmerIncome")
    public Result getFarmerIncome(){
        FarmerIncomeDTO dto1=farmerIncomeService.getFarmerIncome("农户人均收入");
        FarmerIncomeDTO dto2=farmerIncomeService.getFarmerIncome("农户总收入");
        List<FarmerIncomeDTO> list=new java.util.ArrayList<>();
        list.add(dto1);list.add(dto2);
        return list!=null?Result.success(list).setName("获取农户每年人均和总收入")
                :Result.error("查询失败").setName("获取农户每年人均和总收入");
    }

    @ApiOperation("更新农户收入")
    @PutMapping("/farmerIncome")
    public Result updateFarmerIncome(@RequestBody FarmerIncome farmerIncome){
        boolean result=farmerIncomeService.updateFarmerIncome(farmerIncome);
        return result?Result.success().setName("更新农户每年人均和总收入")
                :Result.error("更新失败").setName("更新农户每年人均和总收入");
    }

    @PostMapping("/farmerIncome")
    @ApiOperation("新增农户收入")
    public Result InsrtFarmerIncome(@RequestBody FarmerIncome farmerIncome){
        boolean result=farmerIncomeService.insertFarmerIncome(farmerIncome);
        return result?Result.success().setName("新增农户每年人均和总收入")
                :Result.error("新增失败").setName("新增农户每年人均和总收入");
    }

    @DeleteMapping("/farmerIncome/{name}/{time}")
    @ApiOperation("根据时间和名字删除收入")
    public Result DeleteFarmerIncome(@PathVariable("name") String name,@PathVariable("time") String time){
        boolean result=farmerIncomeService.deleteFarmerIncome(name,time);
        return result?Result.success().setName("根据时间和名字删除某天的农户收入")
                :Result.error("删除失败").setName("根据时间和名字删除某天的农户收入");
    }//失败
}
