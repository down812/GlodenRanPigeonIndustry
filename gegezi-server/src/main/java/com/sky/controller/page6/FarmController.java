package com.sky.controller.page6;


import com.sky.dto.YearFarmDTO;
import com.sky.entity.Farm;
import com.sky.result.Result;
import com.sky.service.FarmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@Slf4j
@Api(tags="鸽子入栏，出栏情况")
public class FarmController {
    @Autowired
    private FarmService farmService;


    @ApiOperation("获取总的入栏，出栏情况")
    @GetMapping("/farm")
    public Result getIOSum(){
        List<Farm> list=farmService.getIOSum();
        return list!=null?Result.success(list).setName("获取总的入栏，出栏情况")
                :Result.error("查询失败").setName("获取总的入栏，出栏情况");
    }

    @ApiOperation("获取不同鸽子每年的入栏，出栏情况")
    @GetMapping("/farm/year")
    public Result getIOYear(){
        List<YearFarmDTO> list=farmService.getIOYear();
        return list!=null?Result.success(list).setName("获取不同鸽子每年的入栏，出栏情况")
                :Result.error("查询失败").setName("获取不同鸽子每年的入栏，出栏情况");
    }

    @ApiOperation("更新某年的鸽子入栏出栏")
    @PutMapping("/farm")
    public Result updateFarm(@RequestBody Farm farm){
        boolean result=farmService.updateFarm(farm);
        return result?Result.success().setName("更新某年的鸽子入栏出栏")
                :Result.error("更新失败").setName("更新某年的鸽子入栏出栏");
    }

    @ApiOperation("新增某年的鸽子入出栏情况")
    @PostMapping("/farm")
    public Result insertFarm(@RequestBody Farm farm){
        boolean result=farmService.insertFarm(farm);
        return result?Result.success().setName("新增某年的鸽子入出栏情况")
                :Result.error("新增失败").setName("新增某年的鸽子入出栏情况");
    }

    @ApiOperation("删除某年的鸽子入出栏情况")
    @DeleteMapping("/farm/{time}/{name}")
    public Result deleteFarm(@PathVariable("time") String time,@PathVariable("name") String name){
        boolean result=farmService.deleteFarm(time,name);
        return result?Result.success().setName("删除某年的鸽子入出栏情况")
                :Result.error("删除失败").setName("删除某年的鸽子入出栏情况");
    }
}
