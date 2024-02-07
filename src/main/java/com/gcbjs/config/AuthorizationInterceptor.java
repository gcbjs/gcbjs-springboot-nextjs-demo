package com.gcbjs.config;

import com.gcbjs.demo.exception.DemoException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * @ClassName AuthorizationInterceptor
 * @Description 自定义拦截器
 * @Author yuzhangbin
 * @Date 2024/2/6 15:59
 * @Version 1.0
 **/
@Slf4j
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private static final String AUTH_TOKEN = "1234567890";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("_auth_token");
        if (token == null) {
            throw new DemoException("token is null");
        }
        //从数据库获取token
        if (!Objects.equals(token, AUTH_TOKEN)) {
            throw new DemoException("无效token");
        }
        return Boolean.TRUE;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
