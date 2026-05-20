package com.sky.controller.Page2;

import com.sky.dto.PigeonCagesDTO;
import com.sky.entity.BaseEconomyTrend;
import com.sky.entity.IncubationStatus;
import com.sky.result.Result;
import com.sky.service.IncubationStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/two")
@Api(tags="Page2--鸽笼状态分步")
public class PigeonCagesController {

    @Autowired
    IncubationStatusService incubationStatusService;

    @ApiOperation("查询鸽笼分步饼图")
    @GetMapping("/getPigeonAgeDistribution")
    public Result getPigeonAgeDistribution(){
     return Result.success(incubationStatusService.getPigeonCages()).setName("鸽笼状态分布饼图");
    }

    @ApiOperation(value = "根据名字来修改鸽笼分步饼图数据")
    @PutMapping("/updateIncubationStatus")
    public Result updateIncubationStatus(@RequestBody IncubationStatus incubationStatus){
        boolean result = incubationStatusService.updateIncubation(incubationStatus);
        return result ? Result.success().setName("根据名字来修改鸽笼分步饼图数据") : Result.error("修改失败");
    }
}
