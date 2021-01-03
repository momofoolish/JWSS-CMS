package com.jwss.cms.controller.api;

import com.jwss.cms.entity.render.Result;
import com.jwss.cms.util.SeverSystemInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/admin")
public class SystemInfoApi {
    @Resource
    SeverSystemInfo severSystemInfo;

    //服务器监控api
    @GetMapping("/watch")
    public Result watch(){
        return new Result(1, severSystemInfo.watch());
    }
}
