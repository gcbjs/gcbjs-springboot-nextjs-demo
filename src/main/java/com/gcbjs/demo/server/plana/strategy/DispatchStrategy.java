package com.gcbjs.demo.server.plana.strategy;

import com.gcbjs.demo.mappers.model.UserInfo;

/**
 * @ClassName DispatchStrategy
 * @Description 分配策略
 * @Author yuzhangbin
 * @Date 2024/1/24 15:50
 * @Version 1.0
 **/
public interface DispatchStrategy {

    /**
    * 分配
    * @param cmd
    * @return com.gcbjs.demo.mappers.model.UserInfo
    * @date: 2024/1/25 08:18
    */
    UserInfo dispatch(DispatchStrategyCmd cmd);
}
