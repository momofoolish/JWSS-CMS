package com.jwss.cms.controller.admin.user;

import com.jwss.cms.service.user.UserService;
import com.jwss.cms.util.SqlUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
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
        List<String> cols = SqlUtils.colsGenerator("id", "name", "account", "password", "mail", "roles", "register_date");
        return SqlUtils.pageResultGenerator(userService.selectByPage(p, l, map, cols));
    }

    //删除单个用户
    @PostMapping("/user/delete")
    public Object delete(@RequestParam Map<String, Object> map) {
        // return new Result(1, userService.delete(map.get("id")));
        return null;
    }

    //批量删除用户
    @PostMapping("/user/delete/batch")
    public Object userDelete(@RequestParam Map<String, Object> map) {
        // return new Result(1, userService.deleteBatch(map));
        return null;
    }

    //更新用户信息
    @PostMapping("/user/updateRoles")
    public Object updateState(@RequestParam Map<String, Object> map) {
        String role = (String) map.get("roles");
        // int re = userService.updateUserRoles(map.get("id").toString(), role);
        // return new Result(1, re);
        return null;
    }
}
