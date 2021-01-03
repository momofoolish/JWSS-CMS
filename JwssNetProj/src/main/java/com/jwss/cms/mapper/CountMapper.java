package com.jwss.cms.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface CountMapper {
    /**
     * 查询一篇文章的评论总数
     *
     * @param aid 文章id
     * @return 评论总数
     */
    Integer selectCommentTotalByAid(Integer aid);

    /**
     * 更新文章点赞量
     *
     * @param aid 文章id
     * @param value 数量
     */
    Integer updateArticleLikes(Integer aid, Integer value);

    /**
     * 更新文章阅读量
     *
     * @param aid 文章id
     * @param value 数量
     */
    Integer updateArticleReads(Integer aid, Integer value);

    /**
     * 统计一篇文章的评论数量
     * @param aid 文章id
     * @return 1
     */
    Integer updateCountComment(Integer aid);
}
