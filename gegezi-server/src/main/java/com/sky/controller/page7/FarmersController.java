package com.sky.controller.page7;

import com.sky.dto.FarmersDTO;
import com.sky.entity.Slaughterhouse;
import com.sky.result.Result;
import com.sky.service.FarmersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@Api(tags = "Page7:养殖户数据表")
public class FarmersController {

    @Autowired
    private FarmersService farmersService;

    @GetMapping("/getFarmers")
    @ApiOperation("获取养殖户数据")
    public Result getFarmers() {
        log.info("获取养殖户数据");
        List<FarmersDTO> list = farmersService.getFarmers();
        return list == null ? Result.error("查询失败").setName("获取养殖户数据") : Result.success(list).setName("获取养殖户数据");
    }
    @DeleteMapping("/deleteFarmerByName/{name}")
    @ApiOperation("根据养殖户名删除数据")
    public Result deleteFarmerByName(@PathVariable String name) {
        log.info("根据养殖户名删除数据: {}", name);
        boolean isDeleted = farmersService.deleteFarmerByName(name);
        return isDeleted ? Result.success().setName("删除养殖户数据") : Result.error("删除失败").setName("删除养殖户数据");
    }

    @PostMapping("/addFarmer")
    @ApiOperation("新增养殖户数据")
    public Result addFarmer(@RequestBody FarmersDTO farmersDTO) {
        log.info("新增养殖户数据: {}", farmersDTO);
        boolean isAdded = farmersService.addFarmer(farmersDTO);
        return isAdded ? Result.success().setName("新增养殖户数据") : Result.error("新增失败").setName("新增养殖户数据");
    }













}
