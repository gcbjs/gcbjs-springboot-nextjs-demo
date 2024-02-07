package com.gcbjs.config;

import com.gcbjs.demo.exception.DemoException;
import com.gcbjs.demo.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * @ClassName WebExceptionHandler
 * @Description http 全局异常拦截配置
 * @Author yuzhangbin
 * @Date 2024/2/6 16:30
 * @Version 1.0
 **/
@Slf4j
@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<Void> handlerException(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error("系统异常",ex);
        return Result.fail("系统繁忙，请稍后重试.....", "500");
    }

    @ExceptionHandler(value = DemoException.class)
    public Result<Void> handlerBusinessException(DemoException ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.error("业务异常",ex);
        return Result.fail(ex.getMessage(), "500");

    }
}
