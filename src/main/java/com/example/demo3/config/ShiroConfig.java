package com.example.demo3.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.security.krb5.Realm;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * shiroFilter
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterBean(@Qualifier("securityManager") SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //访问拦截
        Map<String,String> filterChainMap=new LinkedHashMap<>();
        filterChainMap.put("/LoginController/login","anon");
        filterChainMap.put("/LoginController/logout","logout");
        filterChainMap.put("/*","authc");

        //设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

        return  shiroFilterFactoryBean;
    }


    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager  securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    @Bean("userRealm")
    public UserRealm realm() {
        UserRealm realm = new UserRealm();
        //默认密码匹配是明文匹配
        return realm;
    }
    @Bean("userRealm")
    public UserRealm realm(@Qualifier("matcher") CredentialsMatcher matcher){
        UserRealm realm=new UserRealm();
//        realm密码匹配方法
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    @Bean("matcher")
    public CredentialsMatcher credentialsMatcher()
    {
        HashedCredentialsMatcher    matcher=new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5");//算法名称
        matcher.setHashIterations(1);//加密次数
        return matcher;
    }
}
