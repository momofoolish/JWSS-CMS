package com.jwss.cms.service.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwss.cms.entity.ResultCode;
import com.jwss.cms.entity.render.Result;
import com.jwss.cms.entity.sqldata.Article;
import com.jwss.cms.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    ArticleMapper articleMapper;

    /**
     * 随机3条博客文章
     *
     * @return 文章列表
     */
    public Result randomArticleList() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id");
        int total = articleMapper.selectCount(queryWrapper);
        //一个有 pageTotal 页，每页3条
        int pageTotal = total / 3;
        long pageNum = Math.round(Math.random() * (pageTotal - 1));
        IPage<Article> page = new Page<>(pageNum, 3, total);
        queryWrapper.select("id", "title", "description", "label", "cover", "create_date",
                "reads_number","comment_number");
        IPage<Article> articleList = articleMapper.selectPage(page, queryWrapper);
        return new Result(1, articleList);
    }

    /**
     * 文章搜索
     *
     * @param key 索引值
     * @return 文章列表
     */
    public Result searchArticleList(String key) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", key);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        if (articleList.size() == 0) {
            queryWrapper.like("label", key);
            articleList = articleMapper.selectList(queryWrapper);
        }
        return new Result(1, articleList);
    }

    /**
     * 查一篇文章细节
     * @param aid 文章id
     * @return 文章
     */
    public Result selectDetail(int aid) {
        Article article = articleMapper.selectById(aid);
        if (StringUtils.isEmpty(article)) {
            return new Result(0, ResultCode.ARTICLE_NO_EXIST);
        }
        return new Result(1, article);
    }
}
