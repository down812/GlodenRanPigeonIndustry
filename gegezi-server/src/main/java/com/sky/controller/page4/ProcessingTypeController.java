package com.sky.controller.page4;

import com.sky.dto.ProcessingTypesDTO;
import com.sky.entity.ProcessingTypes;
import com.sky.result.Result;
import com.sky.service.ProcessingTypesService;
import com.sky.service.ProcessingVarietiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.sky.changLiang.numberOfSelectingDay;

@RestController
@RequestMapping("/ProcessingType")
@Slf4j
@CrossOrigin
@Api(tags="深加工品种接口")
public class ProcessingTypeController {

    @Autowired
    private ProcessingTypesService processingTypeService;


    @GetMapping("/getTodayTotal/{today}")
    @ApiOperation("查询今日加工数")
    public Result getTodayTotal(@PathVariable String today) {
        log.info("按今日日期查询今日加工总数：{}", today);
        ProcessingTypes total= processingTypeService.getTotal(today);
        return total == null ? Result.error("查询失败" ).setName("获取今日加工总数") : Result.success(total);
    }

    @PutMapping("/update")
    @ApiOperation("修改指定日期和名字的数据")
    public Result updateProcessingTypes(@RequestBody ProcessingTypes processingTypes) {
        log.info("修改指定日期和名字的数据：{}", processingTypes);
        boolean result = processingTypeService.updateProcessingTypes(processingTypes);
        return result ? Result.success("修改成功").setName("修改指定日期和名字的数据") : Result.error("修改失败").setName("修改指定日期和名字的数据");
    }

    @PostMapping("/add")
    @ApiOperation("增加加工数据")
    public Result addProcessingTypes(@RequestBody ProcessingTypes processingTypes) {

        try {
            // 将传入的 Date 类型时间格式化为 yyyy-MM-dd 字符串
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(processingTypes.getTime());

            // 这里将格式化后的日期字符串重新转换回 Date 类型，以适应服务层逻辑
            Date formattedDateObj = formatter.parse(formattedDate);
            processingTypes.setTime(formattedDateObj);
        } catch (ParseException e) {
            return Result.error("日期格式错误").setName("删除指定日期和名字的加工数据");
        }
        log.info("增加加工数据：{}", processingTypes);
        boolean result = processingTypeService.addProcessingTypes(processingTypes);
        return result ? Result.success("增加成功").setName("增加加工数据") : Result.error("增加失败，日期和名字已存在").setName("增加加工数据");
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除指定日期和名字的加工数据")
    public Result deleteProcessingTypes(@RequestBody ProcessingTypes processingTypes) {
        try {
            // 将传入的 Date 类型时间格式化为 yyyy-MM-dd 字符串
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = formatter.format(processingTypes.getTime());

            // 这里将格式化后的日期字符串重新转换回 Date 类型，以适应服务层逻辑
            Date formattedDateObj = formatter.parse(formattedDate);
            processingTypes.setTime(formattedDateObj);
        } catch (ParseException e) {
            return Result.error("日期格式错误").setName("删除指定日期和名字的加工数据");
        }
        log.info("删除指定日期和名字的加工数据：{}", processingTypes);
        boolean result = processingTypeService.deleteProcessingTypes(processingTypes);
        return result ? Result.success("删除成功").setName("删除指定日期和名字的加工数据") : Result.error("删除失败").setName("删除指定日期和名字的加工数据");
    }





/*
*
     * 查询从当日开始近7天的每日加工数,从当日开始查询，如果某天没有数据，则在该位置返回0

*/
    @GetMapping("/getTotalByDays")
    @ApiOperation("查询近7天的每日加工数")
    public Result getTotalByDays(){
        log.info("查询每日加工总数");
        ProcessingTypesDTO list = processingTypeService.getTotalByDays( numberOfSelectingDay);
        return list == null ? Result.error("查询失败" ).setName("查询近7日的每日加工数") : Result.success(list).setName("查询近7日的每日的加工数");
    }

/*
*
     * 查询从当前月份开始近12月的每月总加工数,从当前月份开始查询，如果某月没有数据，则在该位置返回0
*/
    @GetMapping("/getTotalByMonths")
    @ApiOperation("查询近12月的每月的加工数")
    public Result getTotalByMonths(){
        log.info("查询近12月的每月的加工数");
        ProcessingTypesDTO list = processingTypeService.getTotalByMonths();
        return list == null ? Result.error("查询失败").setName("查询近12月的每月的加工数") : Result.success(list).setName("查询近12月的每月的加工数");
    }
}
