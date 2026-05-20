package com.sky.service.impl;




import com.sky.dto.ProcessingVarietiesDTO2;
import com.sky.entity.ProcessingVarieties;
import com.sky.mapper.ProcessingVarietiesMapper;
import com.sky.service.ProcessingVarietiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 谭晋曦
* @description 针对表【processing_varieties】的数据库操作Service实现
* @createDate 2025-02-23 17:06:10
*/
@Service
public class ProcessingVarietiesServiceImpl implements ProcessingVarietiesService {

    @Autowired
    private ProcessingVarietiesMapper processingVarietiesMapper;

    @Override
    public List<ProcessingVarieties> getVarieties() {
        return processingVarietiesMapper.getVarieties();
    }


    @Override
    public boolean updateVarieties(ProcessingVarietiesDTO2 dto) {
        List<String> names = dto.getName();
        List<Integer> numbers = dto.getNumbers();

        if (names.size() != numbers.size()) {
            throw new IllegalArgumentException("名称和数量数组长度不一致");
        }

        for (int i = 0; i < names.size(); i++) {
            ProcessingVarieties processingVarieties = new ProcessingVarieties();
            processingVarieties.setId(i + 1); // 假设数据库中 id 从 1 开始
            processingVarieties.setName(names.get(i));
            processingVarieties.setNumbers(numbers.get(i));
            processingVarietiesMapper.updateProcessingVarieties(processingVarieties);
        }

        return true;
    }
}




