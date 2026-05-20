package com.sky.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.sky.config.UserDTO;
import com.sky.entity.User;
import com.sky.mapper.LoginMapper;
import com.sky.properties.JwtProperties;
import com.sky.result.Result;
import com.sky.service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static com.sky.utils.RedisConstants.*;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public Result login(User loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        if (username == null || password == null) {
            return Result.error("用户名或密码不能为空");
        }

        // 缓存1：用户名为key（用于快速登录验证）
        String userKey = LOGIN_USER_KEY + username;



        // 检查Redis缓存
        String userJson = stringRedisTemplate.opsForValue().get(userKey);
        if(userJson != null) {
            //防止缓存穿透
            if(StrUtil.isBlank(userJson)) {
                return Result.error("用户名或密码错误");
            }
            try {
                User cachedUser = JSONUtil.toBean(userJson, User.class);
                if(cachedUser != null && cachedUser.getPassword() != null) {
                    if(cachedUser.getPassword().equals(password)) {
                        log.info("从缓存获取用户: id={}, username={}", cachedUser.getId(), cachedUser.getUsername());
                        //token缓存没过期，登录之后应该返回原token值


                        // 从缓存中获取已有token（新增逻辑）
                        String tokenKey = LOGIN_TOKEN_KEY + "*"; // 需要先查询已有token
                        Set<String> keys = stringRedisTemplate.keys(LOGIN_TOKEN_KEY + "*");
                        if(keys != null && !keys.isEmpty()) {
                            String existingToken = keys.iterator().next().replace(LOGIN_TOKEN_KEY, "");
                            return Result.success(existingToken).setName("登录成功！");
                        }
                    }
                    return Result.error("用户名或密码错误");
                }
            } catch (Exception e) {
                log.error("Redis缓存解析错误", e);
            }
        }


        // 查询数据库
        User user = loginMapper.selectUserByUsername(username);
        if(user == null) {
            stringRedisTemplate.opsForValue().set(userKey, "", 5, TimeUnit.MINUTES);
            return Result.error("用户名或密码错误");
        }

        // 校验用户对象完整性
        if(user.getId() == null || user.getUsername() == null || user.getPassword() == null) {
            log.error("用户数据不完整: {}", user);
            return Result.error("用户数据异常");
        }

        if(!user.getPassword().equals(password)) {
            stringRedisTemplate.opsForValue().set(userKey, "", 5, TimeUnit.MINUTES);
            return Result.error("用户名或密码错误");
        }


        //5.保存用户到redis
        //(1)随机生成token，作为登录令牌。  UUID.randomUUID()：这是一个静态方法，用于生成一个随机的 UUID 对象。
        String token= UUID.randomUUID().toString(true);



        //(2)将User对象转化为HashMap存储（增加空值保护）
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) ->
                                fieldValue != null ? fieldValue.toString() : "")); // 添加空值判断


        //（3）统一使用字符串存储用户信息
        String userJsonStr = JSONUtil.toJsonStr(user);
        stringRedisTemplate.opsForValue().set(userKey, userJsonStr, LOGIN_USER_TTL, TimeUnit.MINUTES);

        //（4）存储token到Redis
        // 缓存2：token为key（用于接口鉴权）
        String tokenKey = LOGIN_TOKEN_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey,userMap);
        stringRedisTemplate.expire(tokenKey,LOGIN_TOKEN_TTL,TimeUnit.MINUTES);
        log.info("存储用户到Redis: {}", tokenKey);
        return Result.success(token).setName("登录成功！");

//        return generateTokenResult(user);
    }

//    private Result generateTokenResult(User user) {
//        // 生成JWT token
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", user.getId());
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .signWith(SignatureAlgorithm.HS256, jwtProperties.getAdminSecretKey())
//                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAdminTtl()))
//                .compact();
//
//        // 构造返回结果
//        Map<String, Object> result = new HashMap<>();
//        result.put("user", user);
//        result.put("token", token);
//        return Result.success(result).setName("登录成功！");
//    }

}
