package com.sky.controller.page6;

import com.sky.entity.QuannanxianEconomy;
import com.sky.result.Result;
import com.sky.service.QuannanxianEconomyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin
@Api(tags="获取全南县总经济产值、养殖企业、从业人员")
public class QuannanxianEconoController {
    @Autowired
    private QuannanxianEconomyService service;

    @ApiOperation("获取全南县总经济产值、养殖企业、从业人员")
    @GetMapping("/quannanEco")
    public Result getGDEcono(){
        QuannanxianEconomy gde=service.getGDEcono();
        return gde!=null?Result.success(gde).setName("获取全南县总经济产值、养殖企业、从业人员")
                :Result.error("查询失败").setName("获取全南县总经济产值、养殖企业、从业人员");
    }

    @ApiOperation("更新全南县总经济产值、养殖企业、从业人员")
    @PutMapping("/quannanEco")
    public Result updateQuannanxianEconomy(@RequestBody QuannanxianEconomy quannanxianEconomy){
        boolean result=service.updateGDEcono(quannanxianEconomy);
        return result?Result.success("更新成功").setName("更新全南县总经济产值、养殖企业、从业人员")
                :Result.error("更新失败").setName("更新全南县总经济产值、养殖企业、从业人员");

    }


}
