package com.sky.service.impl;

import com.sky.dto.FarmersDTO;
import com.sky.entity.Farmers;
import com.sky.mapper.FarmersMapper;
import com.sky.service.FarmersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FarmersServiceImpl implements FarmersService {


    @Autowired
    private FarmersMapper farmersMapper;



    @Override
    public List<FarmersDTO> getFarmers() {
        List<Farmers> farmersList = farmersMapper.selectAllFarmers();
        return farmersList.stream()
                .map(farmer -> new FarmersDTO(farmer.getName(), farmer.getRegion(), farmer.getNumbers()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteFarmerByName(String name) {
        return farmersMapper.deleteFarmerByName(name) > 0;
    }

    @Override
    public boolean addFarmer(FarmersDTO farmersDTO) {
        Farmers farmers = new Farmers();
        farmers.setName(farmersDTO.getName());
        farmers.setRegion(farmersDTO.getRegion());
        farmers.setNumbers(farmersDTO.getNumbers());
        return farmersMapper.insertFarmer(farmers) > 0;
    }
}
