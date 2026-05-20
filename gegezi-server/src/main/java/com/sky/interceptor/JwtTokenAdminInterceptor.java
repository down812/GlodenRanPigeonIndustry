package com.sky.interceptor;

import com.sky.constant.JwtClaimsConstant;
import com.sky.properties.JwtProperties;
import com.sky.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.sky.utils.RedisConstants.LOGIN_CODE_KEY;
import static com.sky.utils.RedisConstants.LOGIN_USER_TTL;

/**
 * jwt令牌校验的拦截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 声明 ThreadLocal 变量用于存储新生成的 Token
    private static final ThreadLocal<String> NEW_TOKEN_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // GET请求直接放行
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        //如果当前拦截到的不是Controller方法（比如静态资源请求、视图请求等），就直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }



        // 1. 从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);

            // 存储原始token到ThreadLocal
            NEW_TOKEN_THREAD_LOCAL.set(token);  // <-- 新增

            // 检查过期时间
            if (claims.getExpiration().before(new Date())) {
                response.setStatus(401);
                return false;
            }

            return true;
        } catch (Exception ex) {
            log.error("JWT校验异常：", ex);
            response.setStatus(401);
            return false;
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String originalToken = NEW_TOKEN_THREAD_LOCAL.get();
        if (originalToken != null) {
            // 将原始token设置到响应头
            response.setHeader(jwtProperties.getAdminTokenName(), originalToken);  // <-- 修改
        }
        NEW_TOKEN_THREAD_LOCAL.remove();
    }
}
