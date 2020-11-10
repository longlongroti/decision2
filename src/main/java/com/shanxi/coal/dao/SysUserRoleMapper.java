package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    void insertBatch(List<SysUserRole> list);

    List<SysUserRole> listByUserId(@Param("userId") String userId);

    void deleteByUserId(@Param("userId") String uuid);
}
