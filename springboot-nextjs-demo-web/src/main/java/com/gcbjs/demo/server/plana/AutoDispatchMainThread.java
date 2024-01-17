package com.gcbjs.demo.server.plana;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.Executor;

/**
 * @ClassName AutoDispatchMainThread
 * @Description 自动派单主线程
 * @Author yuzhangbin
 * @Date 2024/1/16 10:04
 * @Version 1.0
 **/
@Slf4j
@Component
public class AutoDispatchMainThread implements CommandLineRunner {


    @Resource
    private Executor ticketTaskExecutor;
    @Resource
    private TicketAppService ticketAppService;
    @Resource
    private UserQueryService userAppService;

    private boolean isStop = false;

    @Override
    public void run(String... args) {
        log.info("自动派单主线程启动");
        try{
            while (!isStop) {
                Long ticketId = TicketQueue.getInstance().takeTicket();
                if (Objects.nonNull(ticketId)) {
                    createDispatchTaskThread(ticketId);
                }
            }
        }catch (Exception e){
            log.error("自动派单主线程异常",e);
        }
    }

    private void createDispatchTaskThread(Long ticketId) {
        DispatchTaskThread dispatchTaskThread = new DispatchTaskThread(ticketId,
                ticketAppService,
                userAppService);
        ticketTaskExecutor.execute(dispatchTaskThread);
    }
}
