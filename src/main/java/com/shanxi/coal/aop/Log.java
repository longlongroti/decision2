package com.shanxi.coal.aop;

import com.shanxi.coal.dao.SysLogMapper;
import com.shanxi.coal.domain.SysLog;
import com.shanxi.coal.domain.SysUser;
import com.shanxi.coal.utils.IpAddressUtil;
import com.shanxi.coal.utils.MyUtils;
import liquibase.util.MD5Util;
import liquibase.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Aspect
@Component
public class Log {

    @Resource
    SysLogMapper sysLogMapper;

    @Pointcut("execution(public * com.shanxi.coal.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        SysLog sysLog = getInfo();
        SysUser sessionUser = MyUtils.getSessionUser();
        if (sessionUser != null && !sysLog.getRequestUrl().contains("/login")) {
            saveLog(joinPoint, sysLog.getRequestUrl(), sysLog.getRequestMethod(), sysLog.getIp(), sessionUser);
            return;
        }
        saveLog(joinPoint, sysLog.getRequestUrl(), sysLog.getRequestMethod(), sysLog.getIp());
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
    }

    //后置异常通知
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp) {
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
    @After("webLog()")
    public void after(JoinPoint joinPoint) {
        SysLog sysLog = getInfo();
        if (sysLog.getRequestUrl().contains("/login") && sysLog.getRequestMethod().equalsIgnoreCase("post")) {
            SysUser sessionUser = MyUtils.getSessionUser();
            if (sessionUser != null) {
                saveLog(joinPoint, sysLog.getRequestUrl(), sysLog.getRequestMethod(), sysLog.getIp(), sessionUser);
            }
        }
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        try {
            Object o = pjp.proceed();
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    private SysLog getInfo() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String requestUrl = request.getRequestURL().toString();
        String requestMethod = request.getMethod();
        String ip = IpAddressUtil.getIpAdrress(request);
        SysLog sysLog = new SysLog();
        sysLog.setRequestMethod(requestMethod);
        sysLog.setRequestUrl(requestUrl);
        sysLog.setIp(ip);
        return sysLog;
    }

    private void saveLog(JoinPoint joinPoint, String requestUrl, String requestMethod, String ip) {
        SysLog sysLog = setSysLog(joinPoint);
        sysLog.setIp(ip);
        sysLog.setRequestMethod(requestMethod);
        sysLog.setRequestUrl(requestUrl);
        sysLogMapper.insertSelective(sysLog);
    }

    private void saveLog(JoinPoint joinPoint, String requestUrl, String requestMethod, String ip, SysUser sessionUser) {
        SysLog sysLog = setSysLog(joinPoint);
        sysLog.setCreatedBy(sessionUser != null ? sessionUser.getUuid() : "");
        sysLog.setCreatedByOrg(sessionUser != null ? sessionUser.getOrgId() : "");
        sysLog.setIp(ip);
        sysLog.setRequestMethod(requestMethod);
        sysLog.setRequestUrl(requestUrl);
        sysLog.setUserId(sessionUser != null ? sessionUser.getUuid() : "");
        sysLog.setUserName(sessionUser != null ? sessionUser.getUserName() : "");
        sysLogMapper.insertSelective(sysLog);
    }

    private SysLog setSysLog(JoinPoint joinPoint) {
        SysLog sysLog = new SysLog();
        sysLog.setUuid(UUID.randomUUID().toString());
        if (joinPoint != null && joinPoint.getSignature() != null && StringUtils.isNotEmpty(joinPoint.getSignature().getDeclaringTypeName()) && StringUtils.isNotEmpty(joinPoint.getSignature().getName())) {
            sysLog.setClassMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        }
        return sysLog;
    }
}
