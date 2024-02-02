package com.gcbjs.demo.json;

import com.gcbjs.demo.util.Result;
import com.gcbjs.redis.twolevelcache.v3.CacheType;
import com.gcbjs.redis.twolevelcache.v3.DoubleCache;
import com.github.benmanes.caffeine.cache.Cache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheController
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/1 16:43
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private Cache<String, Object> caffeineCache;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;



    @RequestMapping(value = "/getTest",method = RequestMethod.GET)
    public Result<List<String>> getTest(@RequestParam("name") String name){
        String key = "test:caffeine:"+ name;
        List<String> result = (List<String>) caffeineCache.get(key,
                k -> {
                    log.info("从caffeine缓存中获取数据失败");
                    if (redisTemplate.hasKey(key)) {
                        log.info("从缓存中获取数据");
                        Object obj = redisTemplate.opsForList().range(key, 0, -1);
                        return obj;
                    }
                    log.info("从数据库中获取数据");
                    List<String> list = List.of("aa", "bbb", "ccc");
                    redisTemplate.opsForList().leftPushAll(key, list);
                    redisTemplate.expire(key, 120, TimeUnit.SECONDS);
                    return list;
                });
        return Result.success(result);
    }

    @DoubleCache(cacheName = "test:caffeine",key = "#name",type = CacheType.FULL)
    @RequestMapping(value = "/getTest2",method = RequestMethod.GET)
    public Result<List<String>> getTest2(@RequestParam("name") String name){
        log.info("调用数据库");
        return Result.success(List.of("aaaa", "bbbb", "cccc"));
    }



//    @Caching(evict = {
//            //删除这个分区下面这个key
////            @CacheEvict(value = "test", key = "'getTest'")
//            @CacheEvict(value = "test", allEntries = true)
//    })
    @RequestMapping(value = "/deleteCache",method = RequestMethod.DELETE)
    public Result<Boolean> deleteCache(@RequestParam("name") String name){
        String key = "test:caffeine:"+ name;
        redisTemplate.delete(key);
        caffeineCache.invalidate(key);
        return Result.success(true);
    }
}
