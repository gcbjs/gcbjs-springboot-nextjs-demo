package com.gcbjs.demo.mappers;

import com.gcbjs.demo.json.param.QueryScheduleParam;
import com.gcbjs.demo.mappers.model.ScheduleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScheduleMapper {

    int insert(ScheduleInfo record);

    int batchInsert(@Param("records") List<ScheduleInfo> records);

    List<ScheduleInfo> getListByDate(@Param("date") String date);


    List<ScheduleInfo> getScheduleList(@Param("userId") Long userId,
                                       @Param("year") String year,
                                       @Param("month") String month);
}