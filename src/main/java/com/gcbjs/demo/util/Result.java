package com.gcbjs.demo.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName Result
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/23 22:16
 * @Version 1.0
 **/
@Data
public class Result<T> implements Serializable {

    /**
     * 请求结果
     */
    private Boolean success;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String message;
    /**
     * 返回数据对象
     */
    private T data;

    private static final String SUCCESS_CODE = "200";

    private static final String FAIL_CODE ="500";

    private static final String DEFAULT_MESSAGE = "success";

    public static <T>Result<T> success() {
        Result<T> resultDTO = new Result<T>();
        resultDTO.setCode(SUCCESS_CODE);
        resultDTO.setMessage(DEFAULT_MESSAGE);
        resultDTO.setSuccess(Boolean.TRUE);
        return resultDTO;
    }

    public static <T>Result<T> success(T data) {
        Result<T> resultDTO = success();
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T>Result<T> success(String message,T data) {
        Result<T> resultDTO = success();
        resultDTO.setMessage(message);
        resultDTO.setData(data);
        return resultDTO;
    }

    public static <T>Result<T> fail(String message) {
        Result<T> resultDTO = new Result<T>();
        resultDTO.setSuccess(Boolean.FALSE);
        resultDTO.setMessage(message);
        resultDTO.setCode(FAIL_CODE);
        return resultDTO;
    }

    public static <T>Result<T> fail(String message, String code) {
        Result<T> resultDTO = fail(message);
        if (Objects.nonNull(code)) {
            resultDTO.setCode(code);
        }
        return resultDTO;
    }

    public  boolean isSuccess() {
        if (this.success != null) {
            return this.success;
        }
        return false;
    }
}
