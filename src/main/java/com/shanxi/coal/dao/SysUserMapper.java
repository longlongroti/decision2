package com.shanxi.coal.dao;

import com.shanxi.coal.domain.SysAuditor;
import com.shanxi.coal.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> listUser(SysUser where);

    List<SysAuditor> listAuditors(@Param("roleId") String roleId, @Param("list") List<String> list);

    List<SysAuditor> listAuditorByRoleId(@Param("roleId") String roleId);

    List<SysUser> listSpecialAuth();

    SysUser selectUserExtra(String uuid);

    SysUser selectByUserName(@Param("userName") String username);

    String selectUserNameById(String uuid);

    List<SysUser> selectOrgBoss();

    SysUser selectBy(@Param("userName") String userName, @Param("password") String password);

    List<SysUser> selectByJob(String assignee);

    List<SysAuditor> listAuditor(SysUser where);
}
