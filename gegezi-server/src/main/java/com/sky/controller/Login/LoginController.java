package com.sky.controller.Login;




import com.sky.entity.User;
import com.sky.result.Result;
import com.sky.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Api(tags="登录接口")
@RequestMapping("/Login")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody User loginForm) {
        // 直接返回服务层的结果，不再额外封装
        return loginService.login(loginForm);
    }

}