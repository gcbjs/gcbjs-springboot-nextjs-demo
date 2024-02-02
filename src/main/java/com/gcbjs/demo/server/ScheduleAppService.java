package com.gcbjs.demo.server;

import com.gcbjs.demo.json.param.QueryScheduleParam;
import com.gcbjs.demo.mappers.ScheduleMapper;
import com.gcbjs.demo.mappers.UserInfoMapper;
import com.gcbjs.demo.mappers.model.ScheduleInfo;
import com.gcbjs.demo.mappers.model.UserInfo;
import com.gcbjs.demo.server.cmd.ScheduleCreateCmd;
import com.gcbjs.demo.util.Page;
import com.gcbjs.demo.util.RedisLock;
import com.github.benmanes.caffeine.cache.Cache;
import com.google.common.collect.Lists;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @ClassName ScheduleAppService
 * @Description 排班应用服务
 * @Author yuzhangbin
 * @Date 2024/1/18 15:11
 * @Version 1.0
 **/
@Slf4j
@Service
public class ScheduleAppService {

    @Resource
    private ScheduleMapper scheduleMapper;
    @Resource
    private RedisLock redisLock;
    @Resource
    private UserInfoMapper userInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean scheduling(ScheduleCreateCmd cmd) {
        String value = UUID.randomUUID().toString();
        String key = "scheduling:userId:" + cmd.getUserId();
        try {
            boolean lock = redisLock.lock(key, value, 1000L);
            if (!lock) {
                log.error("获取锁失败");
                return false;
            }
            UserInfo userInfo = userInfoMapper.findByUserId(cmd.getUserId());
            if (Objects.isNull(userInfo)) {
                log.error("用户不存在");
                return false;
            }
            //TODO 判断当天是否有排班
            scheduleMapper.batchInsert(cmd.getDetails().stream().map(detail ->
                    new ScheduleInfo(cmd.getUserId(),
                            detail.getDate(),
                            detail.getShiftInfoEnum(),
                            userInfo.getName(),
                            userInfo.getMobile())
            ).toList());
            return true;
        }catch (Exception e){
            return false;
        }finally {
            redisLock.releaseLock(key, value);
        }
    }


    /**
     * 根据制定日期获取当天的排版人员id
     */
    public List<ScheduleInfo> getUserIdsByDate(LocalDate date) {
        List<ScheduleInfo> list = scheduleMapper.getListByDate(date.toString());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list;
    }

}
