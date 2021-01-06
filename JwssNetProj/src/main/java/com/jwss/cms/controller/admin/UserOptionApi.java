package com.jwss.cms.controller.admin;

import com.jwss.cms.entity.render.Result;
import com.jwss.cms.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/")
public class UserOptionApi {
    @Resource
    UserService userService;

    //分页查询用户
    @GetMapping("/user/page")
    public Object userPage(@RequestParam Map<String, Object> map) {
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        return userService.selectByPage(p, l, map);
    }

    //删除单个用户
    @PostMapping("/user/delete")
    public Object delete(@RequestParam Map<String, Object> map) {
        return new Result(1, userService.delete(map.get("id")));
    }

    //批量删除用户
    @PostMapping("/user/delete/batch")
    public Object userDelete(@RequestParam Map<String, Object> map) {
        return new Result(1, userService.deleteBatch(map));
    }

    //更新用户信息
    @PostMapping("/user/updateRoles")
    public Object updateState(@RequestParam Map<String, Object> map) {
        String role = (String) map.get("roles");
        int re = userService.updateUserRoles(map.get("id").toString(), role);
        return new Result(1, re);
    }
}
