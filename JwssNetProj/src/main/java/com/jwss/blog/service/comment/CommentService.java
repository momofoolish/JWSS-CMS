package com.jwss.blog.service.comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jwss.blog.entity.render.Result;
import com.jwss.blog.entity.sqldata.Comment;
import com.jwss.blog.entity.sqldata.Feedback;
import com.jwss.blog.entity.sqldata.User;
import com.jwss.blog.mapper.CommentMapper;
import com.jwss.blog.mapper.FeedbackMapper;
import com.jwss.blog.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CommentService {
    @Resource
    UserMapper userMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    FeedbackMapper feedbackMapper;

    /**
     * 用户评论文章
     *
     * @param content 评论内容
     * @param aid     文章id
     * @return 评论成功信息
     */
    public Result userComment(String content, int aid) {
        Comment comment = new Comment();
        comment.setArticleId(aid);
        comment.setContent(content);
        comment.setCreateDate(new Date());
        comment.setUserId(userIdGet());
        return new Result(1, commentMapper.insert(comment));
    }

    /**
     * 用户反馈
     *
     * @param content 反馈内容
     * @return 反馈成功信息
     */
    public Result userFeedBack(String content) {
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        //插入反馈信息
        feedback.setUserId(userIdGet());
        feedback.setCreateDate(new Date());
        return new Result(1, feedbackMapper.insert(feedback));
    }

    //获取用户id
    private String userIdGet(){
        Subject subject = SecurityUtils.getSubject();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("id")
                .lambda()
                .eq(User::getAccount, subject.getPrincipal().toString());
        User user = userMapper.selectOne(queryWrapper);
        return user.getId();
    }
}
