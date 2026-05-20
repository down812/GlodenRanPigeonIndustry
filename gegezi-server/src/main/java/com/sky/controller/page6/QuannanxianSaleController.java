package com.sky.controller.page6;

import com.sky.dto.QuannanxianSaleDTO;
import com.sky.entity.QuannanxianSale;
import com.sky.result.Result;
import com.sky.service.QuannanxianSaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@Api(tags="全南县各品种销量")
public class QuannanxianSaleController {
    @Autowired
    private QuannanxianSaleService service;
    @ApiOperation("获取全南县各品种的销售额占比")
    @GetMapping("/quannanxianSale")
    public Result getSaleRatio(){
        QuannanxianSaleDTO dto=service.getSaleRatio();
        return dto!=null?Result.success(dto).setName("获取全南县各品种的销售额占比")
                :Result.error("查询失败").setName("获取全南县各品种的销售额占比");
    }

    @ApiOperation("更新全南县各品种销售额")
    @PutMapping("/quannanxianSale")
    public Result updateSale(@RequestBody QuannanxianSale quannanxianSale){
        boolean result=service.updateSale(quannanxianSale);
        return result?Result.success().setName("更新全南县各品种销售额")
                :Result.error("更新失败").setName("更新全南县各品种销售额");
    }

    @PostMapping("/quannanxianSale")
    @ApiOperation("新增全南县品种数量")
    public Result InsertSale(@RequestBody QuannanxianSale quannanxianSale){
        boolean result=service.insertSale(quannanxianSale);
        return result?Result.success().setName("新增全南县品种数量")
                :Result.error("新增失败").setName("新增全南县品种数量");
    }

    @DeleteMapping("/quannanxianSale/{name}")
    @ApiOperation("根据名字删除全南县品种数量")
    public Result DeleteSale(@PathVariable String name){
        boolean resutl=service.deleteSale(name);
        return resutl?Result.success().setName("根据名字删除全南县品种数量")
                :Result.error("删除失败").setName("根据名字删除全南县品种数量");
    }



}
