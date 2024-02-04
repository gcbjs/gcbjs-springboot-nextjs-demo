package com.gcbjs.highconcurrency.antitheftbrush.limiter.captcha;

import com.gcbjs.demo.exception.DemoException;
import com.gcbjs.highconcurrency.antitheftbrush.limiter.captcha.model.RateLimitRule;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CaptchAppService
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/4 14:14
 * @Version 1.0
 **/
@Slf4j
@Service
public class CaptchaAppService {


    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private RateLimitRuleMapper rateLimitRuleMapper;

    /**
     * 发送验证码
     * 固定时间内限制发送条数
     * @param
     * @return java.lang.String
     * @date: 2024/2/4 14:16
     */
    public String sendCaptchaCode(String mobile,String ruleKey) {
        RateLimitRule rule = rateLimitRuleMapper.selectByKey(ruleKey);
        if (Objects.isNull(rule)) {
            throw new DemoException("限流规则不存在");
        }
        String countKey = "rate_limit:" + mobile + ":" + System.currentTimeMillis() / rule.getPeriod();
        log.info("key:{}",countKey);
        Long count = redisTemplate.opsForValue().increment(countKey,1);
        if (count > rule.getLimit()) {
            throw new DemoException("超出限制条数");
        }
        // 设置过期时间
        redisTemplate.expire(countKey, rule.getPeriod(), TimeUnit.MILLISECONDS);
        return new Random().nextInt(100000) + "";
    }


}
