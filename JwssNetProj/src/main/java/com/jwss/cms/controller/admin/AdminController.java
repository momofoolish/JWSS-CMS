package com.jwss.cms.controller.admin;

import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.service.comment.CommentService;
import com.jwss.cms.service.comment.FeedbackService;
import com.jwss.cms.service.comment.impl.CommentServiceImpl;
import com.jwss.cms.service.user.OnlineService;
import com.jwss.cms.service.user.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/jwss/admin")
public class AdminController {
    @Resource
    ArticleService articleService;
    @Resource
    FeedbackService feedbackService;
    @Resource
    UserServiceImpl userService;
    @Resource
    OnlineService onlineService;

    @GetMapping("")
    public String adminIndex(Model model){
        model.addAttribute("title","AdminHome");
        model.addAttribute("admin", onlineService.userInfo());
        return "admin/index";
    }

    @GetMapping("/default")
    public String systemInfo(Model model){
        model.addAttribute("articleList", articleService.select(1, 8));
        model.addAttribute("feedbackList", feedbackService.selectByNew( 5));
        model.addAttribute("userList", userService.queryUserNewList(8));
        return "admin/subject/default";
    }

    @GetMapping("/article/operation")
    public String adminArticleOperation(Model model){
        model.addAttribute("title","文章操作");
        model.addAttribute("admin", onlineService.userInfo());
        return "admin/subject/article/operation";
    }

    @GetMapping("/article/examine")
    public String adminArticleExamine(Model model){
        model.addAttribute("title","文章审核列表");
        model.addAttribute("admin", onlineService.userInfo());
        return "admin/subject/article/examine";
    }

    @GetMapping("/user/operation")
    public String userOperation(Model model){
        model.addAttribute("title","网站用户");
        model.addAttribute("admin", onlineService.userInfo());
        return "admin/subject/user/operation";
    }

    @GetMapping("/user/examine")
    public String adminUserExamine(Model model){
        model.addAttribute("title","作者审核列表");
        model.addAttribute("admin", onlineService.userInfo());
        return "admin/subject/user/examine";
    }

}
