package com.gcbjs.demo.json;

import com.gcbjs.demo.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SignController
 * @Description http接口鉴权
 * 1、API Key + API Secret 实现 API 鉴权:
 * 2、Token 机制实现 API 鉴权
 * 3、基于数字签名的鉴权
 * 4、IP地址访问控制
 * 5、多因素身份认证
 * @Author yuzhangbin
 * @Date 2024/2/6 11:36
 * @Version 1.0
 **/
@RestController
@RequestMapping("sign")
public class SignController {


    @RequestMapping(value = "testHttpAuthentication", method = RequestMethod.GET)
    public Result<String> testHttpAuthentication() {
        return Result.success("testHttpAuthentication");
    }
}
