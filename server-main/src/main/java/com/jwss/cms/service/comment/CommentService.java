package com.jwss.cms.service.comment;

public interface CommentService {
    /**
     * 用户评论文章
     *
     * @param content   评论内容
     * @param articleId 文章id
     * @return 评论成功信息
     */
    int userComment(String content, String articleId);
}
