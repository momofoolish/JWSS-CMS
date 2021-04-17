package com.jwss.cms.controller.user;

import com.google.code.kaptcha.Producer;
import com.jwss.cms.constant.RedisKeyType;
import com.jwss.cms.constant.ResultCode;
import com.jwss.cms.model.render.Result;
import com.jwss.cms.model.user.TbUser;
import com.jwss.cms.service.user.UserService;
import com.jwss.cms.util.MyEncrypt;
import com.jwss.cms.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/register/")
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Resource
    private MyEncrypt myEncrypt;
    @Resource
    private Producer producer;

    //	查询账号是否存在
    @PostMapping("accountExist")
    public Result accountExist(@RequestParam("account") String account) {
        if (userService.accountExist(account) == 1) {
            return new Result(1, "该账号可以使用");
        } else {
            return new Result(0, "该账号已存在");
        }
    }

    //	生成图片验证码
    @GetMapping("verifyCode")
    public void verificationCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SystemUtils.verifyCodeProduct(request, response, redisTemplate, producer, RedisKeyType.registerKey);
    }

    //	用户注册信息插入
    @PostMapping("join")
    public Result userRegister(@RequestParam("account") String account, @RequestParam("password") String password,
                               @RequestParam("code") String code, @RequestParam("key") String key, HttpServletRequest request) {
        //判断加密钥匙是否正确
        if (key.equals(myEncrypt.encryptPlus("register"))) {
            TbUser user = new TbUser();
            user.setAccount(account);
            user.setPassword(password);
            int resultCode = userService.register(user, code, request);
            if (resultCode > 0) {
                return new Result(1, "注册成功！");
            } else if (resultCode == -1) {
                return new Result(resultCode, ResultCode.SERVER_ERROR);
            } else if (resultCode == -2) {
                return new Result(resultCode, ResultCode.VERIFITY_CODE_ERROR);
            } else if (resultCode == -3) {
                return new Result(resultCode, ResultCode.USER_EXIST_NAME);
            }
        }
        //不正确直接返回错误信息
        return new Result(0, ResultCode.SERVER_ERROR);
    }
}
