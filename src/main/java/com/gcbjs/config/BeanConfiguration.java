package com.gcbjs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName BeanConfiguration
 * @Description 自定义配置类
 * @Author yuzhangbin
 * @Date 2024/1/16 10:38
 * @Version 1.0
 **/
@Configuration
public class BeanConfiguration {

    /**
     * 派单线程池
     *
     * @return
     */
    @Bean("ticketTaskExecutor")
    public Executor ticketTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(2000);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("ticketTaskExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}
