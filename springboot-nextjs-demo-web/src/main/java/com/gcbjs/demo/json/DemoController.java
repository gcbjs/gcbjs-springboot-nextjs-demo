package com.gcbjs.demo.json;

import com.gcbjs.demo.json.vo.UserInfoVO;
import com.gcbjs.demo.mappers.UserInfoMapper;
import com.gcbjs.demo.mappers.model.UserInfo;
import jakarta.annotation.Resource;
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

    @Resource
    private UserInfoMapper userInfoMapper;

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public List<String> getList() {
        return List.of("a","b","c");
    }


    @RequestMapping(path = "/userList",method = RequestMethod.GET)
    public List<UserInfoVO> userList() {
        List<UserInfo> list = userInfoMapper.findAll();
        return list.stream().map(UserInfoVO::of).toList();
    }



}
