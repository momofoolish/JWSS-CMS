package com.jwss.cms.controller.comment;

import com.jwss.cms.entity.render.Result;
import com.jwss.cms.service.comment.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Resource
    CommentService commentService;

    // 用户评论文章
    @PostMapping("/userSay")
    public Result userComment(@RequestParam String content, @RequestParam int aid) {
        return commentService.userComment(content, aid);
    }

    // 用户反馈
    @PostMapping("/feedback")
    public Result userFeedBack(@RequestParam String content) {
        return commentService.userFeedBack(content);
    }
}
