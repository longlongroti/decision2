package com.shanxi.coal.shiro;




/*
 * apache java 轻量级安全框架，有身份认证（authentication），授权(authorization)，密码学(cryptography)和会话管理(sessionManager) AACS
 *
 * 三个核心API:subject (第三方用户或程序 访问安全管理SM), securityManager（安全管理AACS）,Realm(数据库关联)
 * */

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration/*作用为：配置spring容器(应用上下文)*/
public class ShiroConfig {
    /*
     * 创建3个bean
     * 1. ShiroFilterFactoryBean
     * * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * 2. SecurityManager
     * 3. MyShiroRealm
     * /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     */

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        SecurityUtils.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/lan", "anon");
        filterChainDefinitionMap.put("/wechat/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/loginm", "anon");
        filterChainDefinitionMap.put("/sysuser/gopwd", "anon");
        filterChainDefinitionMap.put("/sysuser/updatepwd", "anon");
        filterChainDefinitionMap.put("/page/index", "anon");
        filterChainDefinitionMap.put("/user/captcha", "anon");
        filterChainDefinitionMap.put("/asserts/**", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");

//        filterChainDefinitionMap.put("/authorization/**", "authc,perms[授权委托]");
//        filterChainDefinitionMap.put("/contractReview/**", "authc,perms[合同送审]");
//        filterChainDefinitionMap.put("/contractChange/**", "authc,perms[合同变更]");
//        filterChainDefinitionMap.put("/contractRelieve/**", "authc,perms[合同解除]");
//        filterChainDefinitionMap.put("/pendingAudit/**", "authc,perms[待审流程]");
//        filterChainDefinitionMap.put("/myAudit/**", "authc,perms[我的流程]");
//        filterChainDefinitionMap.put("/auditedAudit/**", "authc,perms[已审流程]");
//        filterChainDefinitionMap.put("/dealer/**", "authc,perms[客商管理]");
//        filterChainDefinitionMap.put("/file/**", "authc,perms[文件]");
//        filterChainDefinitionMap.put("/sysuser/list", "authc,perms[操作人员_list]");
//        filterChainDefinitionMap.put("/sysuser/**", "authc,perms[操作人员]");
//        filterChainDefinitionMap.put("/sysorg/list", "authc,perms[组织结构_list]");
//        filterChainDefinitionMap.put("/sysorg/**", "authc,perms[组织结构]");
//        filterChainDefinitionMap.put("/sysrole/**", "authc,perms[角色]");
//        filterChainDefinitionMap.put("/sysaudit/**", "authc,perms[岗位]");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setLoginUrl("/page/login");/*登录和认证失败的页面*/
        shiroFilterFactoryBean.setSuccessUrl("/page/index");/*认证成功页面*/
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/403");/*未授权页面*/
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }


    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm=new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean(name = "shiroDialect")/*添加这段代码的目的就是为了在thymeleaf中使用shiro的自定义tag*/
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
