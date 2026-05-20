package com.sky.controller.Page2;

import com.sky.entity.BaseGreenhouse;
import com.sky.entity.IncubationRecord;
import com.sky.entity.PigeonAgeSteps;
import com.sky.result.Result;
import com.sky.service.BaseGreenhouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/two")
@Api(tags="Page2--基地大棚生产信息接口")
public class BaseGreenhouseController {

    @Autowired
    BaseGreenhouseService baseGreenhouseService;



    @ApiOperation(value = "获取基地生产大棚信息、孵化机记录分步")
    @GetMapping("/FarmData")
    public Result getPigeonFarmData() {
        // 调用 Service 层获取数据
        return Result.success(baseGreenhouseService.getBaseGreenhouse()).setName("获取基地生产大棚信息、孵化机记录分步");
    }
    @ApiOperation("更新大棚生产信息")
    @PutMapping("/updateBase")
    public Result updateBase(@RequestBody BaseGreenhouse baseGreenhouse) {
        boolean result = baseGreenhouseService.updateBase(baseGreenhouse);
        return result ? Result.success().setName("更新大棚生产信息") : Result.error("更新失败");
    }

    @ApiOperation("更新孵化机记录分步")
    @PutMapping("/updateRecord")
    public Result updateRecord(@RequestBody IncubationRecord incubationRecord) {
        boolean result = baseGreenhouseService.updateRecord(incubationRecord);
        return result ? Result.success().setName("更新孵化机记录分布") : Result.error("更新失败");
    }

    @ApiOperation("获取季度各种数据")
    @GetMapping("/getQuarter")
    public Result getQuarter(){
        return  Result.success(baseGreenhouseService.getQuarter()) .setName("获取季度各种数据");
    }

    @ApiOperation("获取鸽龄分步信息")
    @GetMapping("/getAger")
    public Result getAger(){
        return Result.success(baseGreenhouseService.getAger()).setName("鸽龄分步信息");
    }



    @ApiOperation(value = "根据名字来修改鸽龄分步")
    @PutMapping("/updateAger")
    public Result updateAger(@RequestBody PigeonAgeSteps pigeonAgeSteps){
        boolean result = baseGreenhouseService.updateAger(pigeonAgeSteps);
        return result ? Result.success().setName("根据名字来修改鸽龄分步") : Result.error("修改失败");
    }
}

