package com.sky.mapper;

import com.sky.entity.Farmers;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FarmersMapper {
    List<Farmers> selectAllFarmers();

    int deleteFarmerByName(String name);

    int insertFarmer(Farmers farmers);
}
