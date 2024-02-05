package com.gcbjs.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.gcbjs.util.ApplicationContextUtil;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @ClassName SendErrorMsgAppender
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/4 17:05
 * @Version 1.0
 **/
@Slf4j
public class SendErrorMsgAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {


    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        if (!iLoggingEvent.getLevel().isGreaterOrEqual(Level.ERROR)) {
            return;
        }
        //获取链路id
        String traceId = iLoggingEvent.getMDCPropertyMap().get("traceId");
        Cache<String,Object> caffeineCache = (Cache<String, Object>) ApplicationContextUtil.getBean("caffeineLogCache");
        Object present = caffeineCache.getIfPresent(traceId);
        if(Objects.nonNull(present)){
            return;
        }
        caffeineCache.put(traceId,Boolean.TRUE);
        log.info("异常信息推送钉钉:{}",iLoggingEvent.getMessage());
    }
}
