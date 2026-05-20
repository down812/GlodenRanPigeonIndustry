package com.sky.controller.page5;

import com.sky.dto.SalesStatusDTO;
import com.sky.entity.SalesStatus;
import com.sky.result.Result;
import com.sky.service.SalesStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@Slf4j
@Api(tags="线上商超、农贸市场、大型商超、饭店销量")
public class SalesStatusController {
    @Autowired
    private SalesStatusService salesStatusService;

    @ApiOperation("获取线上商超、农贸市场、大型商超、饭店12个月销量")
    @GetMapping("/sales/status/month")
    public Result getSaleStatus(){
        SalesStatusDTO dto1=salesStatusService.getSaleStatus("线上商超");
        SalesStatusDTO dto2=salesStatusService.getSaleStatus("农贸市场");
        SalesStatusDTO dto3=salesStatusService.getSaleStatus("大型商超");
        SalesStatusDTO dto4=salesStatusService.getSaleStatus("饭店");
        List<SalesStatusDTO> list=new java.util.ArrayList<>();
        list.add(dto1);list.add(dto2);list.add(dto3);list.add(dto4);
        return list!=null?Result.success(list).setName("获取线上商超、农贸市场、大型商超、饭店12个月销量")
                :Result.error("查询失败").setName("获取线上商超、农贸市场、大型商超、饭店12个月销量");
    }

    @ApiOperation("更新线上商超、农贸市场、大型商超、饭店销量")
    @PutMapping("/sales/status")
    public Result UpdateSaleStatus(@RequestBody SalesStatus salesStatus){
        boolean result=salesStatusService.updateSaleStatus(salesStatus);
        return result?Result.success("更新成功").setName("更新线上商超、农贸市场、大型商超、饭店销量")
                :Result.error("更新失败").setName("更新线上商超、农贸市场、大型商超、饭店销量");
    }

    @ApiOperation("新增线上商超、农贸市场、大型商超、饭店销量")
    @PostMapping("/sales/status")
    public Result insertSaleStatus(@RequestBody SalesStatus salesStatus){
        boolean result=salesStatusService.insertSaleStatus(salesStatus);
        return result?Result.success("插入成功").setName("插入线上商超、农贸市场、大型商超、饭店销量")
                :Result.error("插入失败").setName("插入线上商超、农贸市场、大型商超、饭店销量");
    }

    @ApiOperation("按时间删除线上商超、农贸市场、大型商超、饭店销量")
    @DeleteMapping("/sales/status/{time}/{name}")
    public Result deleteSaleStatus(@PathVariable("time") String time,@PathVariable("name") String name){
        boolean result=salesStatusService.deleteSaleStatus(time,name);
        return result?Result.success("删除成功").setName("删除线上商超、农贸市场、大型商超、饭店销量")
                :Result.error("删除失败").setName("删除线上商超、农贸市场、大型商超、饭店销量");
    }

}
