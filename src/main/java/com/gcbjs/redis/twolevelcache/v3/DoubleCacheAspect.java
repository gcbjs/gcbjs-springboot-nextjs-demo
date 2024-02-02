package com.gcbjs.redis.twolevelcache.v3;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DoubleCacheAspect
 * @Description springboot 会自动开启 AOP，并使用 CGLIB 代理模式
 * @AllArgsConstructor 采用构造器注入
 * @Author yuzhangbin
 * @Date 2024/2/2 09:53
 * @Version 1.0
 **/
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class DoubleCacheAspect {

    private final Cache<String, Object> caffeineCache;
    private final RedisTemplate<String, Object> redisTemplate;

    @Pointcut("@annotation(com.gcbjs.redis.twolevelcache.v3.DoubleCache)")
    public void cacheAspect() {
    }

    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        //拼接解析springEl表达式的map
        String[] paramNames = signature.getParameterNames();
        Object[] args = point.getArgs();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            treeMap.put(paramNames[i],args[i]);
        }

        DoubleCache annotation = method.getAnnotation(DoubleCache.class);
        String elResult = ElParser.parse(annotation.key(), treeMap);
        String realKey = annotation.cacheName() + ":" + elResult;

        //强制更新
        if (annotation.type()== CacheType.PUT){
            Object object = point.proceed();
            redisTemplate.opsForValue().set(realKey, object,annotation.l2TimeOut(), TimeUnit.SECONDS);
            caffeineCache.put(realKey, object);
            return object;
        }
        //删除
        else if (annotation.type()== CacheType.DELETE){
            redisTemplate.delete(realKey);
            caffeineCache.invalidate(realKey);
            return point.proceed();
        }

        //读写，查询Caffeine
        Object result = caffeineCache.getIfPresent(realKey);
        if (Objects.nonNull(result)) {
            log.info("get data from caffeine");
            return result;
        }

        //查询Redis
        Object redisCache = redisTemplate.opsForValue().get(realKey);
        if (Objects.nonNull(redisCache)) {
            log.info("get data from redis");
            caffeineCache.put(realKey, redisCache);
            return redisCache;
        }

        log.info("get data from database");
        Object object = point.proceed();
        if (Objects.nonNull(object)){
            //写入Redis
            redisTemplate.opsForValue().set(realKey, object,annotation.l2TimeOut(), TimeUnit.SECONDS);
            //写入Caffeine
            caffeineCache.put(realKey, object);
        }
        return object;
    }
}
