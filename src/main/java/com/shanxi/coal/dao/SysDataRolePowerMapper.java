package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysRolePower;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDataRolePowerMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysRolePower record);

    int insertSelective(SysRolePower record);

    SysRolePower selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysRolePower record);

    int updateByPrimaryKey(SysRolePower record);

    void insertBatch(@Param("list") List<SysRolePower> list);

    List<SysRolePower> listByRoleId(@Param("uuid") String uuid);

    void deleteByRole(@Param("uuid") String uuid, @Param("status") Integer status);

    List<String> listOrgIdByRole(String uuid);
}
