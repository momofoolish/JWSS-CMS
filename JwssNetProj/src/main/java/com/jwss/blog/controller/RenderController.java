package com.jwss.blog.controller;

import com.jwss.blog.entity.render.Result;
import com.jwss.blog.service.CountService;
import com.jwss.blog.service.article.ArticleService;
import com.jwss.blog.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RenderController {
    @Resource
    ArticleService articleService;
    @Resource
    UserService userService;
    @Resource
    CountService countService;

    //渲染主页文章
    @GetMapping("/api/home")
    public Result index() {
        return articleService.randomArticleList();
    }

    //获取作者信息
    @GetMapping("/api/author")
    public Result author() {
        Subject subject= SecurityUtils.getSubject();
        if(StringUtils.isEmpty(subject.getPrincipal())){
            return new Result(1, userService.getUserInfo("Jwss"));
        }
        return new Result(1, userService.getUserInfo(subject.getPrincipal().toString()));
    }

    //文章与点赞量
    @PostMapping("/api/render/number")
    public Result countReadsAndLikes(@RequestParam String types, @RequestParam int aid) {
        return countService.countReadsAndLikes(types, aid);
    }
}