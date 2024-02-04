package com.gcbjs.highconcurrency.antitheftbrush.limiter.captcha;

import com.gcbjs.highconcurrency.antitheftbrush.limiter.captcha.model.RateLimitRule;
import org.apache.ibatis.annotations.Param;

public interface RateLimitRuleMapper {

    int insert(RateLimitRule record);

    RateLimitRule selectByKey(@Param("key") String key);
}