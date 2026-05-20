package com.sky.controller.Login;

import com.sky.result.Result;
import com.sky.service.ForgetPasswordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(tags="忘记密码接口")
@RequestMapping("/Forget")
@RestController
public class ForgetPasswordController {

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    /**
     * 发送验证码接口
     * @param phone 手机号
     * @return 结果
     */
    @ApiOperation("发送验证码")
    @PostMapping("/sendCode")
    public Result sendVerificationCode(@RequestParam String phone) {
        forgetPasswordService.sendVerificationCode(phone);
        return Result.success("验证码发送成功");
    }

    /**
     * 重置密码接口
     * @param phone 手机号
     * @param newPassword 新密码
     * @param code 验证码
     * @return 结果
     */
    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestParam String phone, @RequestParam String newPassword, @RequestParam String code) {
        forgetPasswordService.resetPassword(phone, newPassword, code);
        return Result.success("密码重置成功");
    }
}
