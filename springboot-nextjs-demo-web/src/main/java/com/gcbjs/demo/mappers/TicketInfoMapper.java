package com.gcbjs.demo.mappers;

import com.gcbjs.demo.mappers.model.TicketInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
  *@ClassName TicketRepository
  *@Description  工单仓储类
  *@Author yuzhangbin
  *@Date 2024/1/15 16:56
  *@Version 1.0
**/
@Repository
public interface TicketInfoMapper {

    int insert(TicketInfo ticketInfo);

    int update(TicketInfo ticketInfo);

    TicketInfo findByTicketId(@Param("ticketId") Long ticketId);

    List<TicketInfo> findWaitingTickets();
}
