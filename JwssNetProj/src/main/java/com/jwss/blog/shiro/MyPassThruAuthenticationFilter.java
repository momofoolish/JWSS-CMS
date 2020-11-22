package com.jwss.blog.shiro;

import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyPassThruAuthenticationFilter extends PassThruAuthenticationFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        logger.info("进入MyPassThruAuthenticationFilter");
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("跨域拦截");
        if (isLoginRequest(request, response)) {
            return true;
        } else {
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
}
