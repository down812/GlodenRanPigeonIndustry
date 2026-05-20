package com.sky.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Long id;

    private String username;

    private String password;

}
