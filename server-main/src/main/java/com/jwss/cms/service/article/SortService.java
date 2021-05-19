package com.jwss.cms.service.article;

import com.jwss.cms.model.article.TbArticleSort;

import java.util.List;

public interface SortService {
    /**
     * 分页查询
     * @param page 页码
     * @param total 限制条数
     * @return 文章分类列表
     */
    List<TbArticleSort> select(int page, int total);
}
