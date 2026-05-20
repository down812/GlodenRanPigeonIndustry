package com.sky.controller.page4;

import com.sky.dto.ProcessingVarietiesDTO;

import com.sky.dto.ProcessingVarietiesDTO2;
import com.sky.entity.ProcessingVarieties;
import com.sky.result.Result;
import com.sky.service.ProcessingVarietiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ProcessingVarieties")
@Slf4j
@CrossOrigin
@Api(tags="深加工饼图接口")
public class ProcessingVarietiesController {

    @Autowired
    private ProcessingVarietiesService processingVarietiesService;

    @GetMapping("/getVar")
    @ApiOperation("获取深加工饼图")
    public Result getVarieties() {
        log.info("获取深加工饼图");
        List<ProcessingVarieties> total= processingVarietiesService.getVarieties();
        return total == null ? Result.error("查询失败").setName("获取加工品种饼图") : Result.success(total).setName("获取加工品种饼图");
    }

    /*@PutMapping("/updateVar")
    @ApiOperation("修改深加工饼图数据")
    public Result updateVarieties(@RequestBody ProcessingVarieties processingVarieties) {
        boolean success = processingVarietiesService.updateVarieties(processingVarieties);
        return success ? Result.success("修改成功").setName("修改加工品种饼图") : Result.error("修改失败").setName("修改加工品种饼图");
    }*/

    @PostMapping("/updateVar")
    @ApiOperation("更新深加工饼图数据")
    public Result updateVarieties(@RequestBody ProcessingVarietiesDTO2 dto) {
        log.info("更新深加工饼图数据");
        boolean isUpdated = processingVarietiesService.updateVarieties(dto);
        return isUpdated ? Result.success().setName("更新加工品种饼图") : Result.error("更新失败").setName("更新加工品种饼图");
    }















}
