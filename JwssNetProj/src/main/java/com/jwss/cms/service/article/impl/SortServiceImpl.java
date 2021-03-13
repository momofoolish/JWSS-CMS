package com.jwss.cms.service.article.impl;

import com.jwss.cms.dao.article.TbArticleSortDao;
import com.jwss.cms.model.article.TbArticleSort;
import com.jwss.cms.service.article.SortService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SortServiceImpl implements SortService {
    @Resource
    TbArticleSortDao articleSortDao;

    @Override
    public List<TbArticleSort> select(int page, int total) {
        if (page > 0) {
            return articleSortDao.selectByPage((page - 1) * total, total);
        }
        return null;
    }
}
