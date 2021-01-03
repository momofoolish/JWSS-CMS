package com.jwss.cms.service.user;

import com.jwss.cms.entity.ResultCode;
import com.jwss.cms.entity.render.Result;
import com.jwss.cms.util.Sys;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    UserService userService;

    /**
     * 用户登录服务
     *
     * @param account  账号
     * @param passWord 密码
     * @return 返回用户信息
     */
    public Result userLogin(String account, String passWord, String code, HttpServletRequest request) {
        String hostKey = Sys.getClientHost(request);
        String redisCode = redisTemplate.opsForValue().get(hostKey);
        if (!code.equals(redisCode)) {
            return new Result(0, ResultCode.VERIFITY_CODE_ERROR);
        }
        //获得当前用户的登录对象
        Subject subject = SecurityUtils.getSubject();
        //用户名和密码令牌
        UsernamePasswordToken token = new UsernamePasswordToken(account, Sys.md5(passWord));
        //异常捕捉登录失败消息
        try {
            //记住我
            token.setRememberMe(true);
            //将令牌传到shiro提供的login方法验证，需要自定义realm
            subject.login(token);
            //没有异常表示登录成功
            return new Result(1, userService.getUserInfo(account));
        } catch (IncorrectCredentialsException ice) {
            return new Result(0, ResultCode.USER_ERROR_PWD);
        } catch (UnknownAccountException e) {
            return new Result(0, ResultCode.USER_NO_EXIST);
        } catch (ExcessiveAttemptsException eae) {
            return new Result(0, ResultCode.TOO_MANY_VISIT);
        } catch (Exception e) {
            return new Result(0, ResultCode.SERVER_ERROR);
        }
    }
}
