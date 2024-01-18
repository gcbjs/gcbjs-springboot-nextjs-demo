package com.gcbjs.demo.server.cmd;

import com.gcbjs.demo.constants.ShiftInfoEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

/**
 * @ClassName SchedueCreateCmd
 * @Description 排班请求
 * @Author yuzhangbin
 * @Date 2024/1/18 15:22
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleCreateCmd {

    /**
     * 用户id
     */
    Long userId;

    /**
     * 排班详情
     */
    List<Detail> details;


    @Data
    public static class Detail {

        /**
         * 值班年月日
         */
        LocalDate date;
        /**
         * 班次
         */
        ShiftInfoEnum shiftInfoEnum;
    }
}
