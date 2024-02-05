package com.gcbjs.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName MyRedisConfiguration
 * @Description
 * 优点：
 * 使用注解，简化操作
 * 缓存管理器，方便多种实现切换缓存源,如Redis,Guava Cache等
 * 支持事务, 即事物回滚时,缓存同时自动回滚
 * 缺点：
 * 不支持TTL，不能为每个 key 设置单独过期时间 expires time，
 * 针对多线程没有专门的处理，所以当多线程时，是会产生数据不一致性的。
 * @Author yuzhangbin
 * @Date 2024/2/1 14:14
 * @Version 1.0
 **/
@EnableConfigurationProperties(CacheProperties.class)
@EnableCaching
@Configuration
public class MyRedisConfiguration {

    @Bean
    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        //Jackson 库中用于序列化和反序列化 JSON 的核心工具
        ObjectMapper objectMapper = new ObjectMapper();
        //JavaTimeModule提供了对java.time包中的日期和时间类型（例如LocalDate、LocalTime、LocalDateTime等）的序列化和反序列化支持。
        objectMapper.registerModule(new JavaTimeModule());
        //设置了所有属性的访问级别为 ANY，这意味着 Jackson 在序列化和反序列化时会考虑所有属性，包括私有属性和受保护属性。
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 激活了默认类型信息，这会在 JSON 中包含对象类型信息，在反序列化时可以正确识别对象类型。
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        //Spring Data Redis 提供的序列化器，用于将对象序列化为 JSON 格式存储在 Redis 中。
        return new Jackson2JsonRedisSerializer<>(objectMapper,Object.class);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                       Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {


        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //连接工厂，用于建立与 Redis 服务器的连接。
        template.setConnectionFactory(redisConnectionFactory);
        //key序列化
        template.setKeySerializer(new StringRedisSerializer());
        //value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }


    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties,
                                                           Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        //key不需要改变
        config = config.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(
                                jackson2JsonRedisSerializer
                )
        );
        CacheProperties.Redis redisProperties = cacheProperties.getRedis();
        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }
        return config;
    }

}
