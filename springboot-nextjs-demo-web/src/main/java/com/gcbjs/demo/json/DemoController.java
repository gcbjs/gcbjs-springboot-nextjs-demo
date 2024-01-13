package com.gcbjs.demo.json;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/12 17:58
 * @Version 1.0
 **/
@RestController
@RequestMapping("/api")
public class DemoController {

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public List<String> getList() {
        return List.of("a","b","c");
    }
}
