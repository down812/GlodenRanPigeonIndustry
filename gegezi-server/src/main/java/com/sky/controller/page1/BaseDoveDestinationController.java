package com.sky.controller.page1;

import com.sky.entity.BaseDoveDestination;
import com.sky.result.Result;
import com.sky.service.BaseDoveDestinationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@Api(tags="Page1--基地乳鸽去向接口")
@RequestMapping("/one")
@RestController
public class BaseDoveDestinationController {

    @Autowired
    BaseDoveDestinationService baseDoveDestinationService;

    @ApiOperation("查询基地乳鸽去向")
    @GetMapping("/getDestination")
    public Result getDestination(){
     return Result.success( baseDoveDestinationService.getDestination()).setName("基地乳鸽去向接口");
    }

    @ApiOperation("根据名字修改乳鸽去向数据")
    @PutMapping("/updateData")
    public Result updateData(@RequestBody BaseDoveDestination destination) {
        boolean result = baseDoveDestinationService.updateNumbers(destination);
        return result ? Result.success().setName("根据名字修改乳鸽去向数据") : Result.error("修改失败");
    }


//    @ApiOperation("修改去向信息")
//    @PutMapping("/updateDestination")
//    public Result updateDestination(
//            @RequestParam String oldName,
//            @RequestParam String newName,
//            @RequestParam Integer numbers) {
//        boolean result = baseDoveDestinationService.updateDestination(oldName, newName, numbers);
//        return result ? Result.success().setName("修改成功") : Result.error("修改失败");
//    }
//
//    @ApiOperation("新增乳鸽去向")
//    @PostMapping("/addDestination")
//    public Result addDestination(@RequestBody BaseDoveDestination destination) {
//        boolean result = baseDoveDestinationService.addDestination(destination);
//        return result ? Result.success(): Result.error("新增失败");
//    }
//
//    @ApiOperation("删除乳鸽去向")
//    public Result deleteDestination(@RequestParam String name) {
//        boolean result = baseDoveDestinationService.deleteDestination(name);
//        return result ? Result.success() : Result.error("删除失败");
//    }




}

