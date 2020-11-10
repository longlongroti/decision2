package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogMapper {

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(String id);

    List<SysLog> list(SysLog baseLog);

    Long count7days(@Param("year") Integer year, @Param("month") Integer month, @Param("date") Integer date);

    Long countLogin();

    Long countLoginToday();
}
