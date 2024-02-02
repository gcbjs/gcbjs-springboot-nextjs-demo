package com.gcbjs.redis.twolevelcache.v3;

import java.lang.annotation.*;

/**
  *@ClassName DoubleCache
  *@Description 
  *@Author yuzhangbin
  *@Date 2024/2/1 22:45
  *@Version 1.0
**/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DoubleCache {

    String cacheName();

    String key();

    long l2TimeOut() default  120L;

    CacheType type() default CacheType.FULL;
}
