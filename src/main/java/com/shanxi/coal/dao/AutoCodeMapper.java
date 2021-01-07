package com.shanxi.coal.dao;

import com.shanxi.coal.domain.AutoCode;
import org.apache.ibatis.annotations.Param;

public interface AutoCodeMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AutoCode record);

    int insertSelective(AutoCode record);

    AutoCode selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AutoCode record);

    int updateByPrimaryKey(AutoCode record);

    AutoCode selectBy(@Param("remark") String remark, @Param("remark1") String catalogCode, @Param("remark2") String yyyyMM);
}