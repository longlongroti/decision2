package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysUserAuditRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserAuditRoleMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysUserAuditRole record);

    int insertSelective(SysUserAuditRole record);

    SysUserAuditRole selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysUserAuditRole record);

    int updateByPrimaryKey(SysUserAuditRole record);

    void insertBatch(List<SysUserAuditRole> list);

    List<SysUserAuditRole> listByUserId(@Param("userId") String userId);

    void deleteByUserId(@Param("userId") String uuid);

    List<String> listJobCodeByUserId(String uuid);
}
