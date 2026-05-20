package com.sky.controller.page1;


import com.sky.entity.*;
import com.sky.result.Result;
import com.sky.service.BaseConditionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@Api(tags="Page1--基地基础信息的接口")
@RequestMapping("/one")
@RestController


public class BaseMessageController {

    @Autowired
    BaseConditionsService baseConditionsService;

    /**
     * 获取基地各种情况的数量
     * @return
     */
    @ApiOperation(value = "查询基地各种情况的数量")
    @GetMapping("/outNumbers")
    public Result  getOutSlaughterNumbers() {
        return Result.success( baseConditionsService.selectBaseData()).setName("查询基地各种情况的数量");
    }

    /**
     * 根据名字来修改某种情况的数量
     * @return
     */
    @ApiOperation("根据名字来修改某种情况的数量")
    @PutMapping("/updateMessage")
    public Result updateData(@RequestBody BaseConditions baseConditions) {
        boolean result = baseConditionsService.updateMessage(baseConditions);
        return result ? Result.success().setName("根据名字修改乳鸽去向数据") : Result.error("修改失败");
    }


    @ApiOperation("查询基地种鸽、乳鸽养殖规模(按年份)")
    @GetMapping("/getFarmProduction")
    public Result getFarmProduction(){
        return Result.success(baseConditionsService.getFarmProductionSummary()).setName("查询基地种鸽、乳鸽养殖规模(按年份)");
    }

    /**
     * 获取基地经济产值和对应年份
     */
    @ApiOperation(value = "获取基地经济产值和对应年份")
    @GetMapping("/getMoney")
    public Result getMoneyYears(){
        return Result.success(baseConditionsService.getAllByMoney()).setName("查询基地经济产值走势");
    }

    /**
     * 根据年份来修改经济产值
     */
    @ApiOperation(value = "根据年份来修改经济产值")
    @PutMapping("/updateMoney")
    public Result updateMoney(@RequestBody BaseEconomyTrend baseEconomyTrend){
        boolean result = baseConditionsService.updateMoney(baseEconomyTrend);
        return result ? Result.success().setName("根据年份来修改经济产值") : Result.error("修改失败");
    }

        /**
     * ①查询深加工产品分类数量
     * ②查询基地人员分布
     */
    @ApiOperation(value = "查询深加工产品分类数量和基地人员分布")
    @GetMapping("/getTypesNumbers")
    public Result getPersonDeepNumbers(){
        return Result.success(baseConditionsService.getPersonDeep());
    }

    /**
     * 修改深加工产品分类
     */
    @ApiOperation(value = "修改深加工产品分类")
    @PutMapping("/updateDeep")
    public Result updateDeep(@RequestBody ProcessingTypes processingTypes){
        boolean result = baseConditionsService.updateDeep(processingTypes);
        return result ? Result.success().setName("修改深加工产品分类") : Result.error("修改失败");
    }
    /**
     * 修改基地人员分布
     */
    @ApiOperation(value = "修改基地人员分布")
    @PutMapping("/updatePerson")
    public Result updatePerson(@RequestBody BasePersonnelDistribution BasePersonnelDistribution){
        boolean result = baseConditionsService.updatePerson(BasePersonnelDistribution);
        return result ? Result.success().setName("修改基地人员分布") : Result.error("修改失败");
    }
}
