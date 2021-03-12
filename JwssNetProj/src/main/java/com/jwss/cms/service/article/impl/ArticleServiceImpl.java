package com.jwss.cms.service.article.impl;

import com.jwss.cms.dao.article.TbArticleDao;
import com.jwss.cms.model.article.TbArticle;
import com.jwss.cms.service.article.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    TbArticleDao articleDao;

    @Override
    public int insert(TbArticle article) {
        return 0;
    }

    @Override
    public int delete(TbArticle article) {
        return 0;
    }

    @Override
    public int update(TbArticle article) {
        return 0;
    }

    @Override
    public List<TbArticle> select(int page, int total) {
        if (page > 0) {
            return articleDao.selectByPage((page - 1) * total, total);
        }
        return null;
    }

    @Override
    public TbArticle queryDetail(String aid) {
        return articleDao.selectByPrimaryKey(aid);
    }

    @Override
    public List<TbArticle> selectBySearch(int page, int total, String keyWord, List<String> cols) {
        if (page > 0) {
            //如果keyWord不为空，则为全局搜索
            if (!"".equals(keyWord)) {
                page = 1;
            }
            return articleDao.selectBySearch((page - 1) * total, total, keyWord, cols);
        }
        return null;
    }

    @Override
    public List<Map<String, String>> selectByTable(int page, int total, int state, String keyWord) {
        if (page > 0) {
            if (state < 0) {
                return articleDao.selectByTable((page - 1) * total, total, keyWord);
            }else {
                return articleDao.selectByTableExam((page - 1) * total, total, state, keyWord);
            }
        }
        return null;
    }
}
