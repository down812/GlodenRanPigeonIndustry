package com.sky.service;

import com.sky.result.Result;

public interface ForgetPasswordService {
    /**
    * 发送验证码
    * @param phone 手机号
    */
    Result sendVerificationCode(String phone);

    /**
     * 重置密码
     * @param phone 手机号
     * @param newPassword 新密码
     * @param code 验证码
     */
    Result resetPassword(String phone, String newPassword, String code);
}
