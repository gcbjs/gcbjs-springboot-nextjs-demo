package com.gcbjs.demo.util;

/**
 * @ClassName DateTimeUtils
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/1/24 17:00
 * @Version 1.0
 **/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateTimeUtils {

    /**
     * 将 LocalDateTime 转化为指定格式的字符串
     *
     * @param dateTime 要转化的 LocalDateTime 对象
     * @param pattern  指定的日期时间格式
     * @return 格式化后的字符串
     */
    public static String formatLocalDateTime(LocalDateTime dateTime, String pattern) {
        if (Objects.isNull(dateTime)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }
}

