package com.gcbjs.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @ClassName DemoApplication
 * @Description springboot-next.js 启动类
 * @Author yuzhangbin
 * @Date 2024/1/12 17:20
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("com.gcbjs.demo.mappers")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
