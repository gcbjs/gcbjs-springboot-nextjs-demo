package com.gcbjs.demo.json;

import com.gcbjs.demo.util.Result;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @ClassName TraceController
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/4 21:46
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("trace")
public class TraceController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("ping")
    public Result<String> ping() {
        log.info("进入ping");
        return Result.success("pong study");
    }

    @RequestMapping("log")
    public Result<String> log() {
        log.info("this is info log");
        log.error("this is error log");
        log.debug("this is debug log");
        log.warn("this is warn log");
        log.trace("this is trace log");
        log.error("this is error log2");
        log.error("this is error log3");
        return Result.success("123");
    }

    @RequestMapping(value = "http", method = RequestMethod.GET)
    public Result<String> httpQuery() {

        String studyUrl = "http://localhost:8080/trace/ping";
        URI studyUri = URI.create(studyUrl);
        String study = restTemplate.getForObject(studyUri, String.class);
        log.info("study:{}", study);

        String floorUrl = "http://localhost:8080/trace/log";
        URI floorUri = URI.create(floorUrl);
        String floor = restTemplate.getForObject(floorUri, String.class);
        log.info("floor:{}", floor);

        String roomUrl = "http://localhost:8080/trace/ping";
        URI roomUri = URI.create(roomUrl);
        String room = restTemplate.getForObject(roomUri, String.class);
        log.info("room:{}", room);
        return Result.success(study + "-------" + floor + "-------" + room + "-------");
    }
}
