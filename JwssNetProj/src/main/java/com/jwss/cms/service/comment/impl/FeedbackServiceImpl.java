package com.jwss.cms.service.comment.impl;

import com.jwss.cms.dao.comment.TbFeedbackDao;
import com.jwss.cms.model.comment.TbFeedback;
import com.jwss.cms.service.BaseServiceImpl;
import com.jwss.cms.service.comment.FeedbackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FeedbackServiceImpl extends BaseServiceImpl implements FeedbackService {
    @Resource
    TbFeedbackDao feedbackDao;

    @Override
    public int userFeedBack(String content) {
        TbFeedback feedback = new TbFeedback();
        feedback.setContent(content);
        //插入反馈信息
        feedback.setUser_id(getUserInfo().getId());
        feedback.setCreate_date(new Date());
        return feedbackDao.insertSelective(feedback);
    }

    @Override
    public List<TbFeedback> selectByNew(int total) {
        return feedbackDao.selectByNew(total);
    }
}
