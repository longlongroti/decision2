package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysUserDataRole;
import com.shanxi.coal.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDataRoleMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysUserDataRole record);

    int insertSelective(SysUserDataRole record);

    SysUserDataRole selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysUserDataRole record);

    int updateByPrimaryKey(SysUserDataRole record);

    void insertBatch(List<SysUserDataRole> list);

    List<SysUserDataRole> listByUserId(@Param("userId") String userId);

    void deleteByUserId(@Param("userId") String uuid);
}
