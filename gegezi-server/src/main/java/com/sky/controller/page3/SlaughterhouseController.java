package com.sky.controller.page3;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sky.dto.SlaughterhouseDTO;
import com.sky.entity.Slaughterhouse;
import com.sky.result.Result;
import com.sky.service.SlaughterhouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.sky.changLiang.numberOfSelectingDay;

@RestController
@RequestMapping("/Slaughterhouse")
@Slf4j
@CrossOrigin
@Api(tags="屠宰场接口")
public class SlaughterhouseController {


    @Autowired
    private SlaughterhouseService slaughterhouseService;

    @GetMapping("/getTodayTotal/{today}")
    public Result getTotal(@PathVariable String today) {
        log.info("按今日日期查询今日屠宰总数：{}", today);
        Slaughterhouse total= slaughterhouseService.getTotal(today);
        return total == null ? Result.error("查询失败").setName("获取今日屠宰总数") : Result.success(total).setName("获取今日屠宰数");
    }

    @PutMapping("/updateByDate")
    public Result updateByDate(@RequestBody Slaughterhouse slaughterhouse) {
        log.info("根据日期修改屠宰数据：{}", slaughterhouse);
        boolean result = slaughterhouseService.updateByDate(slaughterhouse);
        return result ? Result.success("修改成功").setName("根据日期修改屠宰数据") : Result.error("修改失败").setName("根据日期修改屠宰数据");
    }


    @PostMapping("/addByDate")
    public Result addByDate(@RequestBody Slaughterhouse slaughterhouse) {
        /*String timeStr = slaughterhouse.getTime().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(timeStr);
            slaughterhouse.setTime(date);
        } catch (ParseException e) {
            return Result.error("日期格式错误，请使用 yyyy-MM-dd 格式");
        }*/
        log.info("根据日期添加屠宰数据：{}", slaughterhouse);
        boolean result = slaughterhouseService.addByDate(slaughterhouse);
        return result ? Result.success("添加成功").setName("根据日期添加屠宰数据") : Result.error("添加失败").setName("根据日期添加屠宰数据");
    }

    @DeleteMapping("/deleteByDate/{date}")
    public Result deleteByDate(@PathVariable String date) {
        log.info("根据日期删除屠宰数据：{}", date);
        boolean result = slaughterhouseService.deleteByDate(date);
        return result ? Result.success("删除成功").setName("根据日期删除屠宰数据") : Result.error("删除失败").setName("根据日期删除屠宰数据");
    }

    /**
     * 查询从当日开始近7天的每日加工数,从当日开始查询，如果某天没有数据，则在该位置返回0
     */
    @GetMapping("/getTotalByDays")
    @ApiOperation("查询近7天的每日屠宰数")
    public Result getTotalByDays(){
        log.info("查询近7天的每日屠宰数");
        SlaughterhouseDTO list = slaughterhouseService.getTotalByDays(numberOfSelectingDay);//numberOfSelectingDay为常量7
        return list == null ? Result.error("查询失败" ).setName("查询近7天的每日屠宰数") : Result.success(list).setName("查询近7天的每日屠宰数");
    }

   /* *
     * 查询从当前月份开始近12月的每月总加工数,从当前月份开始查询，如果某月没有数据，则在该位置返回0
     * */

    @GetMapping("/getTotalByMonths")
    @ApiOperation("查询近12月的每月的屠宰数")
    public Result getTotalByMonths(){
        log.info("查询近12月的每月的屠宰数");
        SlaughterhouseDTO list = slaughterhouseService.getTotalByMonths();
        return list == null ? Result.error("查询失败").setName("查询近12月的每月的屠宰数"): Result.success(list).setName("查询近12月的每月的屠宰数");
    }



}
