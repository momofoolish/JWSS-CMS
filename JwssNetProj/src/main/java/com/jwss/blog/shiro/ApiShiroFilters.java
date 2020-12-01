package com.jwss.blog.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class ApiShiroFilters {

    //配置接口过滤
    ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filtersMap = new HashMap<>();
        filtersMap.put("authc", new MyPassThruAuthenticationFilter());
        filtersMap.put("roles", new RoleFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        Map<String, String> filterCDMap = new LinkedHashMap<>();
        //swagger2免拦截
        filterCDMap.put("/doc.html**", "anon");
        filterCDMap.put("/v2/api-docs", "anon");
        filterCDMap.put("/swagger-resources/**", "anon");
        filterCDMap.put("/webjars/**", "anon");
        //普通页面放行
        filterCDMap.put("/", "anon");
        filterCDMap.put("/index", "anon");
        filterCDMap.put("/about", "anon");
        filterCDMap.put("/register", "anon");
        filterCDMap.put("/article/detail/**", "anon");
        //管理员页面授权
        filterCDMap.put("/jwss/admin/**", "anon");
        //接口过滤
        filterCDMap.put("/api/home", "anon");
        filterCDMap.put("/api/author", "anon");
        filterCDMap.put("/api/article/search", "anon");
        filterCDMap.put("/api/article/detail", "anon");
        filterCDMap.put("/api/login/formLogin", "anon");
        filterCDMap.put("/api/register/**", "anon");
        filterCDMap.put("/api/user/**", "anon");
        //静态资源放行
        filterCDMap.put("/public/**", "anon");
        filterCDMap.put("/favicon.ico", "anon");
        //角色授权过滤器
        filterCDMap.put("/api/comment/userSay", "roles[user,author]");
        filterCDMap.put("/api/comment/feedback", "roles[user,author]");
        filterCDMap.put("/api/article/author/**", "roles[author]");
        //角色静态资源过滤器
        filterCDMap.put("/author/**", "roles[author]");
        filterCDMap.put("/logout", "logout");
        filterCDMap.put("/**", "authc");
        //登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterCDMap);
        return shiroFilterFactoryBean;
    }
}
