package com.gcbjs.demo.server.plana;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName TicketQueue
 * @Description 工单队列
 * @Author yuzhangbin
 * @Date 2024/1/16 10:03
 * @Version 1.0
 **/
public class TicketQueue {

    private static class InstanceHolder {
        static final TicketQueue INSTANCE = new TicketQueue();
    }

    private final BlockingQueue<Long> waitingTicketQueue;

    private TicketQueue() {
        waitingTicketQueue = new LinkedBlockingQueue<>();
    }

    public static TicketQueue getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 获取队列中工单数量
     *
     * @param
     * @return int
     * @date: 2024/1/16 14:01
     */
    public int getSize() {
        return waitingTicketQueue.size();
    }

    /**
    * 添加工单
    * @param ticketId
    * @return void
    * @date: 2024/1/16 14:12
    */
    public void put(Long ticketId) {
        try {
            waitingTicketQueue.put(ticketId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void putAll(List<Long> ticketIds) {
        if (CollectionUtils.isEmpty(ticketIds)) {
            return;
        }
        waitingTicketQueue.addAll(ticketIds);
    }

    /**
    * 获取工单
    * @param 
    * @return java.lang.Long
    * @date: 2024/1/16 14:12
    */
    public Long take() {
        try {
            return waitingTicketQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
