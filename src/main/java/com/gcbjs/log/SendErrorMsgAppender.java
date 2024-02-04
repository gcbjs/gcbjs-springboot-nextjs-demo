package com.gcbjs.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.github.benmanes.caffeine.cache.Cache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName SendErrorMsgAppender
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/4 17:05
 * @Version 1.0
 **/
@Slf4j
@Component
public class SendErrorMsgAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    @Resource
    private Cache<String, Object> caffeineLogCache;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        if (!iLoggingEvent.getLevel().isGreaterOrEqual(Level.ERROR)) {
            return;
        }
        //获取链路id
        String traceId = iLoggingEvent.getMDCPropertyMap().get("traceId");
        System.out.println("traceId:" + traceId);
    }
}
