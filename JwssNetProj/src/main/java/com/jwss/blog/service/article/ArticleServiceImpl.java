package com.jwss.blog.service.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwss.blog.entity.sqldata.Article;
import com.jwss.blog.entity.sqldata.User;
import com.jwss.blog.mapper.ArticleMapper;
import com.jwss.blog.service.BaseService;
import com.jwss.blog.service.BaseServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ArticleServiceImpl extends BaseServiceImpl implements BaseService {
    @Resource
    ArticleMapper articleMapper;

    @Override
    public int insert(Object o) {
        Article article = (Article) o;
        article.setCreateDate(new Date());
        article.setLikesNumber(0);
        article.setCommentNumber(0);
        article.setReadsNumber(1);
        article.setAuthorId(getUserInfo().getId());
        return articleMapper.insert(article);
    }

    @Override
    public int delete(Object o) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Article::getAuthorId, getUserInfo().getId())
                .eq(Article::getId, o);
        return articleMapper.delete(queryWrapper);
    }

    @Override
    public int update(Object o) {
        Article article = (Article) o;
        article.setAlterDate(new Date());
        article.setAuthorId(getUserInfo().getId());
        UpdateWrapper<Article> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(Article::getAuthorId,getUserInfo().getId())
                .eq(Article::getId,article.getId());
        return articleMapper.update(article, updateWrapper);
    }

    @Override
    public Object select(int index, int total) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        IPage<Article> iPage = new Page<>(index, total);
        queryWrapper.select("id", "title", "description", "label", "cover", "create_date",
                "reads_number", "comment_number");
        queryWrapper.lambda().eq(Article::getAuthorId, getUserInfo().getId());
        return articleMapper.selectPage(iPage, queryWrapper);
    }
}