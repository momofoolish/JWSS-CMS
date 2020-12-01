package com.jwss.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jwss/admin")
public class AdminController {

    @GetMapping("/")
    public String adminIndex(Model model){
        model.addAttribute("title","后台首页");
        return "admin/index";
    }
}
