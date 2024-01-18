package com.gcbjs.demo.mappers.model;

import com.gcbjs.demo.constants.WorkStatusEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * @ClassName UserEntity
 * @Description 业务员实体
 * @Author yuzhangbin
 * @Date 2024/1/15 14:33
 * @Version 1.0
 **/
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserInfo {

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
     * 工作状态
     */
    WorkStatusEnum workStatus;

    LocalDateTime createTime;

    /**
     * 更新时间
     */
    LocalDateTime updateTime;

    public void working() {
        this.workStatus = WorkStatusEnum.WORKING;
    }

    public void free() {
        this.workStatus = WorkStatusEnum.FREE;
    }

    public boolean checkNoWorking() {
        return this.workStatus == WorkStatusEnum.FREE;
    }
}
