package com.example.shiro_springboot.config;

import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Shiro_Config {

    @Bean
    public IniRealm getIniRealm(){
        IniRealm iniRealm = new IniRealm("classpath:Shiro.ini");
        return iniRealm;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(IniRealm iniRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //SecurityManager进行效验，需要Realm
        defaultWebSecurityManager.setRealm(iniRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 过滤器就是Shiro进行权限效验的核心，进行认证和授权是需要SecurityManager的
     * */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //设置Shiro的拦截规则
        Map<String, String> hashMap = new HashMap<>();
        //项目的根路径不拦截
        //anon 匿名用户可访问
        //authc 认证用户可访问
        //user 使用RemeberMe的用户可访问
        //perms 对应权限的用户可访问
        //role 对应角色可访问
        hashMap.put("/","anon");
        hashMap.put("/login.html","anon");
        hashMap.put("/login","anon");
        //未认证用户访问页面，默认跳转到login.jsp
        hashMap.put("/**","authc");
        filterFactoryBean.setLoginUrl("/login.html");
        //设置为授权访问的页面路径
        filterFactoryBean.setUnauthorizedUrl("/login.html");
        filterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return filterFactoryBean;
    }
}
