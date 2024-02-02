package com.gcbjs.demo.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName CaffeineConfig
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/1 17:52
 * @Version 1.0
 **/
@Configuration
public class CaffeineConfig {

    @Bean
    public Cache<String,Object> caffeineCache(){
        return Caffeine.newBuilder()
                //初始大小
                .initialCapacity(128)
                .maximumSize(1024)
                .expireAfterWrite(60, TimeUnit.SECONDS)
                .build();
    }
}
