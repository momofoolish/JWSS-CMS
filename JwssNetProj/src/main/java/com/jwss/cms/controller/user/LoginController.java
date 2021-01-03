package com.jwss.cms.controller.user;

import com.jwss.cms.entity.render.Result;
import com.jwss.cms.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    StringRedisTemplate redisTemplate;

    //	登录功能实现
    @PostMapping("formLogin")
    public Result login(HttpServletRequest request, @RequestParam("account") String account, @RequestParam("password") String passWord
            , @RequestParam("code") String code) {
        return loginService.userLogin(account, passWord, code, request);
    }
}
