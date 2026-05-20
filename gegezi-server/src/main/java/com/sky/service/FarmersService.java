package com.sky.service;

import com.sky.dto.FarmersDTO;

import java.util.List;

public interface FarmersService {
    List<FarmersDTO> getFarmers();

    boolean deleteFarmerByName(String name);

    boolean addFarmer(FarmersDTO farmersDTO);
}
