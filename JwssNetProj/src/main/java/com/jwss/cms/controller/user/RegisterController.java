package com.jwss.cms.controller.user;

import com.google.code.kaptcha.Producer;
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
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/register/")
public class RegisterController {
    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate redisTemplate;
    @Resource
    MyEncrypt myEncrypt;
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
        String capText = producer.createText();
        String userIp = SystemUtils.getClientHost(request);
        redisTemplate.opsForValue().set(userIp, capText, 10, TimeUnit.MINUTES);
        logger.info("验证码：" + capText + "\t 用户IP：" + userIp);
        //创建验证码
        BufferedImage bi = producer.createImage(capText);
        //返回验证码图形给客户端
        SystemUtils.responseImageSetting(response);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    //	用户注册信息插入
    @PostMapping("join")
    public Result userRegister(@RequestParam("account") String account, @RequestParam("password") String password,
                               @RequestParam("code") String code, @RequestParam("key") String key, HttpServletRequest request) {
        if(key.equals(myEncrypt.encryptPlus("register"))){
            TbUser user = new TbUser();
            user.setAccount(account);
            user.setPassword(password);
            int resultCode = userService.register(user, code, request);
            if(resultCode == -1){
                return new Result(0, ResultCode.SERVER_ERROR);
            }else if(resultCode == 0){
                return new Result(0, "注册成功！");
            }else if(resultCode == 1){
                return new Result(0, ResultCode.USER_EXIST_NAME);
            }
        }
        return new Result(0, ResultCode.SERVER_ERROR);
    }
}
