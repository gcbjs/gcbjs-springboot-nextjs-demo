package com.gcbjs.java.thread._4_1;

import lombok.extern.slf4j.Slf4j;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @ClassName MultiThread
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/31 17:21
 * @Version 1.0
 **/
@Slf4j
public class MultiThread {

    public static void main(String[] args) {
        //在运行一个main线程时，会同时运行多个其他线程
        //[1] main
        //[2] Reference Handler
        //[3] Finalizer
        //[4] Signal Dispatcher
        //[12] Common-Cleaner
        //[13] Monitor Ctrl-Break
        //[14] Notification Thread
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("["+threadInfo.getThreadId()+"] "+threadInfo.getThreadName());
        }
    }
}
