package com.sky.service.impl;





import com.sky.dto.QuannanxianSaleDTO;
import com.sky.entity.QuannanxianSale;
import com.sky.mapper.QuannanxianSaleMapper;
import com.sky.service.QuannanxianSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【quannanxian_varieties】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class QuannanxianSaleServiceImpl

    implements QuannanxianSaleService {

    @Autowired
    private QuannanxianSaleMapper mapper;

    @Override
    public QuannanxianSaleDTO getSaleRatio() {
        List<QuannanxianSale> list=mapper.getSaleRatio();
        QuannanxianSaleDTO dto=new QuannanxianSaleDTO();
        ArrayList<String> name=new ArrayList<>();
        ArrayList<Double> ratio=new ArrayList<>();
        for(QuannanxianSale tmp:list){
            name.add(tmp.getName());
            ratio.add(tmp.getRatio());
        }
        dto.setName(name);
        dto.setRatio(ratio);
        return dto;
    }

    @Override
    public boolean updateSale(QuannanxianSale quannanxianSale) {
        int rows=mapper.updateSale(quannanxianSale);
        return rows>0;
    }

    @Override
    public boolean insertSale(QuannanxianSale quannanxianSale) {
        QuannanxianSale qxs=mapper.getSaleSin(quannanxianSale.getName());
        if(qxs!=null){
            return mapper.updateSale(quannanxianSale)>0;
        }
        return mapper.insertSale(quannanxianSale)>0;
    }

    @Override
    public boolean deleteSale(String name) {
        return mapper.deleteSale(name)>0;
    }
}




