package com.jwss.cms.controller.comment;

import com.jwss.cms.model.render.Result;
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
    public Result userComment(@RequestParam String content, @RequestParam String aid) {
        return new Result(1, commentService.userComment(content, aid));
    }

}
