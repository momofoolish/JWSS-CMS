package com.jwss.cms.controller.user;

import com.jwss.cms.constant.ResultCode;
import com.jwss.cms.model.render.Result;
import com.jwss.cms.model.user.TbUser;
import com.jwss.cms.service.user.OnlineService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    OnlineService onlineService;

    @GetMapping("/info")
    public Result userInfo(){
        TbUser user=onlineService.userInfo();
        if(StringUtils.isEmpty(user)){
            return new Result(0, ResultCode.NO_LOGIN);
        }
        return new Result(1,user);
    }
}
