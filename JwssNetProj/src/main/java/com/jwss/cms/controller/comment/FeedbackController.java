package com.jwss.cms.controller.comment;

import com.jwss.cms.model.render.Result;
import com.jwss.cms.service.comment.FeedbackService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Resource
    FeedbackService feedbackService;

    // 用户反馈
    @PostMapping("/send")
    public Result userFeedBack(@RequestParam String content) {
        return new Result(1, feedbackService.userFeedBack(content));
    }
}
