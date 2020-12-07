package com.jwss.blog.service.comment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.List;

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

    /**
     * 获取最新反馈
     *
     * @param total 获取条数
     * @return 最新反馈集合
     */
    public List<Feedback> queryFeedBackList(int total) {
        QueryWrapper<Feedback> queryWrapper = new QueryWrapper<>();
        IPage<Feedback> iPage = new Page<>(0, total);
        queryWrapper.select("id", "content", "create_date").orderByDesc("create_date");
        return feedbackMapper.selectPage(iPage, queryWrapper).getRecords();
    }

    //获取用户id
    private String userIdGet() {
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
