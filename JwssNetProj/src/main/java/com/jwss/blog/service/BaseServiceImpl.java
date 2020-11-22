package com.jwss.blog.service;

import com.jwss.blog.entity.sqldata.User;
import com.jwss.blog.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

public class BaseServiceImpl {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    UserService userService;

    protected User getUserInfo() {
        Subject subject = SecurityUtils.getSubject();
        try {
            String account = subject.getPrincipal().toString();
            return userService.getUserInfo(account);
        }catch (Exception e){
            logger.warn("未登录");
            return null;
        }
    }
}