package com.gcbjs.demo.exception;

/**
 * @ClassName DemoException
 * @Description 异常
 * @Author yuzhangbin
 * @Date 2024/1/15 15:48
 * @Version 1.0
 **/
public class DemoException extends RuntimeException {

    private String errCode;

    public DemoException(String errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public DemoException(String message) {
        super(message);
        this.errCode = "500";
    }
}
