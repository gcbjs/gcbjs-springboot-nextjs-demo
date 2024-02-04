package com.gcbjs.highconcurrency.antitheftbrush.limiter.captcha.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
  *@ClassName RateLimitRule
  *@Description 限流规则实体
  *@Author yuzhangbin
  *@Date 2024/2/4 14:32
  *@Version 1.0
**/
@Data
public class RateLimitRule implements Serializable {
    /**
    * 主键
    */
    private Long id;

    /**
    * 限流规则key
    */
    private String key;

    /**
    * 限流阈值
    */
    private Integer limit;

    /**
    * 时间窗口（毫秒）
    */
    private Long period;

    /**
    * 创建时间
    */
    private Date createDate;
}