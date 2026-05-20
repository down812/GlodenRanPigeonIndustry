package com.sky.controller;

import com.sky.dto.CameraDTO;
import com.sky.dto.CameraDTO1;
import com.sky.dto.YSCloudDTO;
import com.sky.result.Result;
import com.sky.service.YSCloudService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/camera")
@Api(tags="摄像头数据接口")
public class CameraController {

    @Autowired
    private YSCloudService ysCloudService;

    @PostMapping("/getDeviceUrls")
    @ApiOperation("获取设备直播地址")
    public Result getDeviceUrls() {
        String deviceName="棚顶";
        log.info("获取设备直播地址: {}", deviceName);
        List<YSCloudDTO> result = ysCloudService.processDeviceUrls(deviceName);
        return result!=null ? Result.success(result):Result.error("获取失败");
    }



    @PostMapping("/addDevice")
    @ApiOperation("添加设备到萤石云平台")
    public Result addDevice(
            @ApiParam(value = "设备序列号", required = true) @RequestParam String deviceSerial,
            @ApiParam(value = "设备验证码", required = true) @RequestParam String validateCode) {
        log.info("添加设备到萤石云平台: 设备序列号={}", deviceSerial);
        boolean result = ysCloudService.addDevice(deviceSerial, validateCode);
        return result ? Result.success("设备添加成功") : Result.error("设备添加失败");
    }
}