package com.sky.service;

import com.sky.dto.BaseEconomyTrendDTO;
import com.sky.entity.BaseEconomyTrend;

public interface BaseEconomyTrendService {
    BaseEconomyTrendDTO getBaseEconomyTrend();

    boolean updateBaseEconomyTrend(BaseEconomyTrend baseEconomyTrend);

    boolean insertBaseEconomyTrend(BaseEconomyTrend baseEconomyTrend);

    boolean deleteBaseEconomyTrend(String time);
}
