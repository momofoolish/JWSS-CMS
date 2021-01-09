package com.jwss.cms.controller.admin;

import com.jwss.cms.service.article.ArticleServiceImpl;
import com.jwss.cms.service.comment.CommentService;
import com.jwss.cms.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/jwss/admin")
public class AdminController {
    @Resource
    ArticleServiceImpl articleServiceImpl;
    @Resource
    CommentService commentService;
    @Resource
    UserService userService;

    @GetMapping("/")
    public String adminIndex(Model model){
        model.addAttribute("title","AdminHome");
        model.addAttribute("articleList", articleServiceImpl.selectNewList(8));
        model.addAttribute("feedbackList", commentService.queryFeedBackList(5));
        model.addAttribute("userList", userService.queryUserNewList(8));
        return "admin/index";
    }

    @GetMapping("/article/operation")
    public String adminArticleOperation(Model model){
        model.addAttribute("title","文章操作");
        return "admin/articleOperation";
    }

    @GetMapping("/user/operation")
    public String userOperation(Model model){
        model.addAttribute("title","网站用户");
        return "admin/userOperation";
    }

    @GetMapping("/article/examine")
    public String adminArticleExamine(Model model){
        model.addAttribute("title","文章审核列表");
        return "admin/articleExamine";
    }

    @GetMapping("/user/examine")
    public String adminUserExamine(Model model){
        model.addAttribute("title","作者审核列表");
        return "admin/userExamine";
    }
}
