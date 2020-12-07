package com.jwss.blog.controller.admin;

import com.jwss.blog.service.article.ArticleServiceImpl;
import com.jwss.blog.service.comment.CommentService;
import com.jwss.blog.service.user.UserService;
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
}
