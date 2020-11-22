package com.jwss.blog.controller;

import com.jwss.blog.entity.sqldata.Menu;
import com.jwss.blog.service.menu.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ViewsController {
    @Resource
    MenuService menuService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("baseTitle", "Jwss");
        model.addAttribute("title", "首页");
        renderMenu(model);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("baseTitle", "Jwss");
        model.addAttribute("title", "登录欢迎━(*｀∀´*)ノ亻!");
        if (isLogin() == 0) {
            return "user/login";
        } else {
            return "index";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("isLogin", "no");
        return "user/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("baseTitle", "Jwss");
        model.addAttribute("title", "用户注册中心");
        if (isLogin() == 0) {
            return "user/register";
        } else {
            return "index";
        }
    }

    @GetMapping("/author/editor")
    public String editor(Model model) {
        model.addAttribute("baseTitle", "Jwss");
        model.addAttribute("title", "文章编辑中心");
        return "content/editor";
    }

    @GetMapping("/author")
    public String author(Model model, @RequestParam(required = false) Integer p) {
        if (!StringUtils.isEmpty(p)) {
            if (p > 1) {
                model.addAttribute("index", p);
                return "author";
            }
            model.addAttribute("index", 1);
            return "author";
        } else {
            model.addAttribute("index", 1);
            return "author";
        }
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/article/detail")
    public String detail(Model model, @RequestParam int aid) {
        model.addAttribute("aid", aid);
        return "detail";
    }

    /**
     * 判断是否已经登录
     *
     * @return 1&0
     */
    private int isLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isEmpty(subject.getPrincipals())) {
            return 0;   //未登录 0
        } else {
            return 1;   //已登录 1
        }
    }

    /**
     * 渲染菜单
     *
     * @param model Model
     */
    private void renderMenu(@NotNull Model model) {
        List<Menu> menuList = menuService.queryMenuList();
        model.addAttribute("menuList", menuList);
        model.addAttribute("isLogin", isLogin());
    }
}
