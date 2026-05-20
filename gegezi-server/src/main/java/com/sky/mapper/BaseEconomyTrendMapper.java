package com.sky.mapper;

import com.sky.entity.BaseEconomyTrend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseEconomyTrendMapper {
    List<BaseEconomyTrend> getBaseEconomyTrend();

    int updateBaseEconomyTrend(BaseEconomyTrend baseEconomyTrend);

    int insertBaseEconomyTrend(BaseEconomyTrend baseEconomyTrend);

    int deleteBaseEconomyTrend(String time);
}
