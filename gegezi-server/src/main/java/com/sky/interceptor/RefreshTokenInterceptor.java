package com.sky.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 刷新token有效期拦截器
 */
@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 这里根据你的业务逻辑实现token刷新逻辑
        // 示例：从请求头获取token，刷新redis中token的有效期
        String token = request.getHeader("token");
        if (token != null) {
            // 刷新token过期时间，根据你的实际过期时间调整
            stringRedisTemplate.expire(token, 30, TimeUnit.MINUTES);
        }
        return true;
    }
}