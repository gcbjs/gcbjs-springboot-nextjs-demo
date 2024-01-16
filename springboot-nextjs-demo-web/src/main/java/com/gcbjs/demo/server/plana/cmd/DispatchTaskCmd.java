package com.gcbjs.demo.server.plana.cmd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DispatchTaskCmd
 * @Description 派单请求参数
 * @Author yuzhangbin
 * @Date 2024/1/16 15:27
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatchTaskCmd {

    /**
     * 工单id
     */
    private Long ticketId;

    /**
     * 用户id
     */
    private Long userId;
}
