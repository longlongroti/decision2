package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysDataRole;

import java.util.List;

public interface SysDataRoleMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysDataRole record);

    int insertSelective(SysDataRole record);

    SysDataRole selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysDataRole record);

    int updateByPrimaryKey(SysDataRole record);

    List<SysDataRole> listRoleAndPower(SysDataRole where);

    List<SysDataRole> listRoles();

}
