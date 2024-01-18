package com.gcbjs.demo.server.plana;

import com.gcbjs.demo.mappers.UserInfoMapper;
import com.gcbjs.demo.mappers.model.UserInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserAppService
 * @Description 业务员应用服务
 * @Author yuzhangbin
 * @Date 2024/1/16 15:54
 * @Version 1.0
 **/
@Service
public class UserQueryService {

    @Resource
    private UserInfoMapper userInfoMapper;


    public List<UserInfo> getUsersByUserIds(List<Long> userIds) {
        return userInfoMapper.fetchListByUserIds(userIds);
    }

}
