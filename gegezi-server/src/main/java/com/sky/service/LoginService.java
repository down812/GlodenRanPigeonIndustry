package com.sky.service;

import com.sky.entity.User;
import com.sky.result.Result;

public interface LoginService {

    public Result login(User loginForm);
}
