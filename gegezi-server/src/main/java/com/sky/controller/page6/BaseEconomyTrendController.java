package com.sky.controller.page6;

import com.sky.dto.BaseEconomyTrendDTO;
import com.sky.entity.BaseEconomyTrend;
import com.sky.result.Result;
import com.sky.service.BaseEconomyTrendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@Api(tags = "总产值")
public class BaseEconomyTrendController {
    @Autowired
    private BaseEconomyTrendService service;


    @ApiOperation("获取每年总产值")
    @GetMapping("/baseEconomyTrend")
    public Result getBaseEconomyTrend(){
        BaseEconomyTrendDTO dto=service.getBaseEconomyTrend();
        return dto!=null?Result.success(dto).setName("获取历年总产值")
                :Result.error("查询失败").setName("获取历年总产值");
    }

    @ApiOperation("更新某天总产值")
    @PutMapping("/baseEconomyTrend")
    public Result updateBaseEconomyTrend(@RequestBody BaseEconomyTrend baseEconomyTrend){
        boolean result=service.updateBaseEconomyTrend(baseEconomyTrend);
        return result?Result.success("更新成功").setName("更新历年总产值")
                :Result.error("更新失败，无该数据").setName("更新历年总产值");
    }

    @PostMapping("/baseEconomyTrend")
    @ApiOperation("插入某天的总产值")
    public Result InsertBaseEconomyTrend(@RequestBody BaseEconomyTrend baseEconomyTrend){
        boolean result=service.insertBaseEconomyTrend(baseEconomyTrend);
        return result?Result.success("插入成功").setName("插入某天的总产值")
                :Result.error("插入失败").setName("插入某天的总产值");
    }

    @DeleteMapping("/baseEconomyTrend/{time}")
    @ApiOperation("根据时间删除某天的总产值")
    public Result DeleteBaseEconomyTrend(@PathVariable String time){
        boolean result=service.deleteBaseEconomyTrend(time);
        return result?Result.success("删除成功").setName("删除某天的总产值")
                :Result.error("删除失败").setName("删除某天的总产值");
    }
}
