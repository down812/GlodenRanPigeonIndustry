package com.sky.controller.page5;

import com.sky.dto.MonthlySalesTrendDTO;
import com.sky.entity.SalesTrend;
import com.sky.result.Result;
import com.sky.service.SalesTrendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
@Slf4j
@Api(tags="乳鸽、种鸽、鸽蛋的价格和销量")
public class SalesTrendController {
    @Autowired
    private SalesTrendService salesTrendService;

    @GetMapping("/sales/trend/month")
    @ApiOperation("获取三种商品12月以来的销售走势")
    public Result getSaleTrend(){
        MonthlySalesTrendDTO dto1=salesTrendService.getSaleTrend("乳鸽");
        MonthlySalesTrendDTO dto2=salesTrendService.getSaleTrend("种鸽");
        MonthlySalesTrendDTO dto3=salesTrendService.getSaleTrend("鸽蛋");
        List<MonthlySalesTrendDTO> lists=new ArrayList<>();
        lists.add(dto1);lists.add(dto2);lists.add(dto3);
        return lists!=null?Result.success(lists).setName("获取乳鸽、种鸽、鸽蛋12月的价格走势")
                :Result.error("查询失败");
    }

    @ApiOperation("更新乳鸽、种鸽、鸽蛋的价格或销量")
    @PutMapping("/sales/trend")
    public Result UpdateSaleTrend(@RequestBody SalesTrend salesTrend){
        boolean result=salesTrendService.updateSaleTrend(salesTrend);
        return result?Result.success("更新成功").setName("更新乳鸽、种鸽、鸽蛋的价格或销量")
                :Result.error("更新失败").setName("更新乳鸽、种鸽、鸽蛋的价格或销量");
    }

    @PostMapping("/sales/trend")
    @ApiOperation("新增乳鸽、种鸽、鸽蛋的价格或销量")
    public Result InsertSaleTrend(@RequestBody SalesTrend salesTrend){
        boolean result=salesTrendService.insertSaleTrend(salesTrend);
        return result?Result.success("插入成功").setName("插入乳鸽、种鸽、鸽蛋的价格或销量")
                :Result.error("插入失败").setName("插入乳鸽、种鸽、鸽蛋的价格或销量");
    }

    @DeleteMapping("/sales/trend/{time}/{name}")
    @ApiOperation("按时间和名字删除乳鸽、种鸽、鸽蛋的价格或销量")
    public Result dDeleteSalesTrend(@PathVariable String time,@PathVariable String name){
        boolean result=salesTrendService.deleteSaleTrend(time,name);
        return result?Result.success("删除成功").setName("删除乳鸽、种鸽、鸽蛋的价格或销量")
                :Result.error("删除失败").setName("删除乳鸽、种鸽、鸽蛋的价格或销量");

    }

}
