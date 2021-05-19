package com.jwss.cms.service.comment.impl;

import com.jwss.cms.dao.comment.TbCommentDao;
import com.jwss.cms.model.comment.TbComment;
import com.jwss.cms.service.BaseServiceImpl;
import com.jwss.cms.service.comment.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CommentServiceImpl extends BaseServiceImpl implements CommentService {

    @Resource
    TbCommentDao commentDao;

    @Override
    public int userComment(String content, String articleId) {
        TbComment comment = new TbComment();
        comment.setArticleId(articleId);
        comment.setContent(content);
        comment.setCreateDate(new Date());
        comment.setUserId(getUserInfo().getId());
        return commentDao.insertSelective(comment);
    }


}
