package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysRole;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> listRoleAndPower(SysRole where);

    List<SysRole> listRoles();

}
