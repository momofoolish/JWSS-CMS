package com.jwss.cms.controller.user;

import com.google.code.kaptcha.Producer;
import com.jwss.cms.constant.RedisKeyType;
import com.jwss.cms.model.render.Result;
import com.jwss.cms.service.user.LoginService;
import com.jwss.cms.util.MyEncrypt;
import com.jwss.cms.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private Producer producer;

    //	登录功能实现
    @PostMapping("formLogin")
    public Result login(HttpServletRequest request, @RequestParam("account") String account
            , @RequestParam("password") String passWord, @RequestParam("code") String code) {
        return loginService.userLogin(account, passWord, code, request);
    }

    //	生成图片验证码
    @GetMapping("verifyCode")
    public void verificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SystemUtils.verifyCodeProduct(request, response, redisTemplate, producer, RedisKeyType.loginKey);
    }
}
