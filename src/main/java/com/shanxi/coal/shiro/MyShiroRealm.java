package com.shanxi.coal.shiro;

import com.shanxi.coal.dao.SysUserMapper;
import com.shanxi.coal.domain.SysRolePower;
import com.shanxi.coal.domain.SysUser;
import com.shanxi.coal.domain.SysUserRole;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private SysUserMapper sysUserMapper;



    /*执行授权逻辑*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo");
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        SysUser sysUserExtra = sysUserMapper.selectUserExtra(sysUser.getUuid());
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        if (sysUserExtra != null && !sysUserExtra.getSysUserRoles().isEmpty()) {
            for (SysUserRole sysUserRole : sysUserExtra.getSysUserRoles()) {
                if (sysUserRole.getSysRole() != null && !sysUserRole.getSysRole().getPowers().isEmpty()) {
                    for (SysRolePower sysRolePower : sysUserRole.getSysRole().getPowers()) {
                        simpleAuthenticationInfo.addStringPermission(sysRolePower.getPower());
                    }
                }
            }
        }
        return simpleAuthenticationInfo;
    }

    /*执行认证逻辑*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("doGetAuthenticationInfo");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUser sysUser = sysUserMapper.selectByUserName(token.getUsername());
        if (sysUser == null) {
            return null;
        }
        List<String> supervised = new ArrayList<>();
        supervised.add(sysUser.getOrgId());
        sysUser.setSupervisedOrg(supervised);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), "");
        return info;
    }
}
