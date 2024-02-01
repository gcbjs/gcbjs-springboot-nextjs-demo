package com.gcbjs.demo.json;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.gcbjs.demo.json.param.DirtyUserData;
import com.gcbjs.demo.server.ScheduleAppService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @ClassName DirtyUserDataListener
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/31 14:53
 * @Version 1.0
 **/
@Slf4j
public class DirtyUserDataListener implements ReadListener<DirtyUserData> {

    private ScheduleAppService scheduleAppService;

    public DirtyUserDataListener(ScheduleAppService scheduleAppService) {
        this.scheduleAppService = scheduleAppService;
    }

    @Override
    public void invoke(DirtyUserData dirtyUserData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
