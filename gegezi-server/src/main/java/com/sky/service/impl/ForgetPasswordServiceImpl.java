package com.sky.service.impl;

import com.sky.entity.User;
import com.sky.mapper.LoginMapper;
import com.sky.result.Result;
import com.sky.service.ForgetPasswordService;
import com.sky.utils.SMSUtils;
import com.sky.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.sky.utils.RedisConstants.FORGET_CODE_KEY;

@Service
@Slf4j
public class ForgetPasswordServiceImpl implements ForgetPasswordService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private LoginMapper userMapper;


//    private static final String SIGN_NAME = "你的短信签名";
//    private static final String TEMPLATE_CODE = "你的短信模板代码";

    @Override
    public Result sendVerificationCode(String phone) {
        if(StringUtils.isEmpty(phone)){
            return Result.error("短信发送失败");
        }
        String redisKey = FORGET_CODE_KEY + phone;
        //2.随机生成六位验证码
        String code = ValidateCodeUtils.generateValidateCode(6).toString();

        //3.调用阿里云提供的短信服务
        SMSUtils.sendMessage("鸽鸽子","",phone,code);

        //4.需要将生成的验证码保存到对应Key的redis中，并设置5分钟过期时间
        stringRedisTemplate.opsForValue().set(redisKey, code, 5, TimeUnit.MINUTES);

        log.info("验证码已发送，手机号：{}，验证码：{}", phone, code);
        return Result.success("验证码短信发送成功");
    }

    @Override
    public Result resetPassword(String phone, String newPassword, String code) {
        String redisKey = FORGET_CODE_KEY + phone;
        // 验证验证码
        String storedCode = stringRedisTemplate.opsForValue().get(redisKey);
        if (storedCode == null || !storedCode.equals(code)) {
            log.error("验证码无效或已过期，手机号：{}", phone);
            try {
                throw new Exception("验证码无效或已过期");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // 根据手机号查询用户
        User user = userMapper.selectUserByUsername(phone);
        if (user == null) {
            log.error("用户不存在，手机号：{}", phone);
            try {
                throw new Exception("用户不存在");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        // 更新用户密码
        user.setPassword(newPassword);
        userMapper.updateUserPassword(user);
        // 删除 Redis 中的验证码
        stringRedisTemplate.delete(FORGET_CODE_KEY+phone+"1");
        log.info("密码重置成功，手机号：{}", phone);
        return Result.success();
    }


}
