package com.sky.interceptor;

import com.sky.config.UserHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // GET请求直接放行
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        //如果当前拦截到的不是Controller方法（比如静态资源请求、视图请求等），就直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //判断是否需要拦截
        if(UserHolder.getUser()==null){
            response.setStatus(401);
            response.getWriter().write("请重新登录!");
            return false;
        }

        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //销毁，以免内存泄漏
        UserHolder.removeUser();
    }


}
