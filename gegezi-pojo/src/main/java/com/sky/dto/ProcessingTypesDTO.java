package com.sky.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProcessingTypesDTO implements Serializable {

    private List<String> timeData;
    private List<Integer> countData;
}
