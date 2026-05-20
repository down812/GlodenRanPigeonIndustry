package com.sky.service.impl;




import com.sky.entity.QuannanxianEconomy;
import com.sky.mapper.QuannanxianEconomyMapper;
import com.sky.service.QuannanxianEconomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 谭晋曦
* @description 针对表【quannanxian_economy】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class QuannanxianEconomyServiceImpl

    implements QuannanxianEconomyService {
@Autowired
private QuannanxianEconomyMapper mapper;
    @Override
    public QuannanxianEconomy getGDEcono() {
        QuannanxianEconomy quannanxianEconomy=mapper.getGDEcono();
        return quannanxianEconomy;
    }

    @Override
    public boolean updateGDEcono(QuannanxianEconomy quannanxianEconomy) {
        int rows=mapper.updateGDEcono(quannanxianEconomy);
        return rows>0;
    }
}




