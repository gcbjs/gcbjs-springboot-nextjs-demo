package com.gcbjs.demo.mappers;

import com.gcbjs.demo.mappers.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
  *@ClassName UserRepository
  *@Description 用户仓储类
  *@Author yuzhangbin
  *@Date 2024/1/15 16:56
  *@Version 1.0
**/
@Repository
public interface UserInfoMapper {

    UserInfo findByUserId(@Param("userId") Long userId);

    List<UserInfo> findAll();

    List<UserInfo> fetchListByUserIds(@Param("userIds") List<Long> userIds);

    int updateStatus(UserInfo userInfo);
}
