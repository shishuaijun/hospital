package com.follow.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangchunjun
 * @date 2020/8/5
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized"); //原为/unauthorized


        //拦截器
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        System.out.println("创建拦截");
        //放行的路径
        //filterMap.put("/**", "anon");//全部放行
        filterMap.put("/login", "anon");
        filterMap.put("/denglu", "anon");
        filterMap.put("/index", "anon");
        filterMap.put("/index1", "anon");
        filterMap.put("/zhuce", "anon");

        //放行静态资源文件
        filterMap.put("/static/**", "anon");

        //配置退出过滤器，其中的具体的推出代码 shiro 已经替我们实现了
        //filterMap.put("/logout", "logout");

        //过滤链定义，从上向下顺序执行，一般将 /** 放在最下面。
        // authc ： 所有的url 都必须认证通过才可以访问；  anon：所有url 都可以匿名访问

        //如果不设置默认会自动寻找web 工程目录下的 "/login.jsp" 页面
        shiroFilterFactoryBean.setLoginUrl("/login");

        //登录成功后跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setSuccessUrl("/index1");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }


    // 登录完成没有跳转展示页面。 我在下面方法少个判断登录。 判断是否登录成功。登录成功跳转那个页面等


    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
