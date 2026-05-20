package com.sky.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @TableName quannanxian_economy
 */

@Data
public class QuannanxianEconomy implements Serializable {
    private Integer id;

    private Integer totalEconomy;

    private Integer farmingEnterprises;

    private Integer employees;

    private static final long serialVersionUID = 1L;
}