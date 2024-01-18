package com.gcbjs.demo.json.vo;

import com.gcbjs.demo.mappers.model.UserInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName UserInfoVO
 * @Description 用户信息VO
 * @Author yuzhangbin
 * @Date 2024/1/15 17:42
 * @Version 1.0
 **/
@Data
public class UserInfoVO implements Serializable {


    /**
     * 用户id
     */
    Long userId;

    /**
     * 姓名
     */
    String name;
    /**
     * 手机号
     */
    String mobile;

    /**
     * 工作状态描述
     */
    String workStatusDesc;
    /**
     * 工作状态
     */
    String workStatus;

    public static UserInfoVO of(UserInfo userInfo) {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserId(userInfo.getUserId());
        userInfoVO.setName(userInfo.getName());
        userInfoVO.setMobile(userInfo.getMobile());
        userInfoVO.setWorkStatus(userInfo.getWorkStatus().name());
        userInfoVO.setWorkStatusDesc(userInfo.getWorkStatus().getDesc());
        return userInfoVO;
    }
}
