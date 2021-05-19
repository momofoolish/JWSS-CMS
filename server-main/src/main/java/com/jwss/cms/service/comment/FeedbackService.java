package com.jwss.cms.service.comment;

import com.jwss.cms.model.comment.TbFeedback;

import java.util.List;

public interface FeedbackService {
    /**
     * 用户反馈
     *
     * @param content 反馈内容
     * @return 反馈成功信息
     */
    int userFeedBack(String content);

    /**
     * 获取最新反馈
     *
     * @param total 获取条数
     * @return 最新反馈集合
     */
    List<TbFeedback> selectByNew(int total);
}

