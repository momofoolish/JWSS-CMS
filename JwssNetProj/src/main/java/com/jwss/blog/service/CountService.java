package com.jwss.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jwss.blog.entity.render.Result;
import com.jwss.blog.entity.sqldata.Article;
import com.jwss.blog.entity.sqldata.User;
import com.jwss.blog.mapper.ArticleMapper;
import com.jwss.blog.mapper.CountMapper;
import com.jwss.blog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CountService {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Resource
    CountMapper countMapper;
    @Resource
    ArticleMapper articleMapper;
    @Resource
    UserMapper userMapper;

    /**
     * 统计阅读量与点赞量
     *
     * @param types 类型
     * @return success
     */
    public Result countReadsAndLikes(String types, Integer id) {
        //取缓存数值
        if (StringUtils.isEmpty(redisTemplate.opsForHash().get(types, id.toString()))) {
            redisTemplate.opsForHash().put(types, id.toString(), 2 + "");
        } else {
            //自增
            redisTemplate.opsForHash().increment(types, id.toString(), 1);
        }
        return new Result(1, "kkk-vvv");
    }

    /**
     * 阅读量与点赞量存进mysql数据库
     * 包括评论数量
     *
     * @param types 类型（reads&likes）
     */
    public void insertCountData(String types) {
        Map<Object, Object> resultList = redisTemplate.opsForHash().entries(types);
        //判断类型
        switch (types) {
            case "reads":
                resultList.forEach((k, v) ->
                        {
                            if (existArticle(Integer.parseInt(k + ""))) {
                                countMapper.updateArticleReads(Integer.parseInt(k + ""),
                                        Integer.parseInt(v + ""));
                                countMapper.updateCountComment(Integer.parseInt(k + ""));
                            }
                        }
                );
                break;
            case "likes":
                resultList.forEach((k, v) ->
                        {
                            if (existArticle(Integer.parseInt(k + ""))) {
                                countMapper.updateArticleLikes(Integer.parseInt(k + ""),
                                        Integer.parseInt(v + ""));
                                countMapper.updateCountComment(Integer.parseInt(k + ""));
                            }
                        }
                );
                break;
            default:
                break;
        }
    }

    /**
     * 统计每一名作者的点赞总量和阅读总量
     */
    public void insertCountDataByUser() {
        //获取所有作者
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("roles", "id").lambda().eq(User::getRoles, "author");
        List<User> userList = userMapper.selectList(userQueryWrapper);
        for (User item : userList) {
            //获取一名作者的所有文章
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("reads_number", "likes_number").lambda()
                    .eq(Article::getAuthorId, item.getId());
            List<Article> articleList = articleMapper.selectList(queryWrapper);
            int reads = 0;
            int likes = 0;
            for (Article article : articleList) {
                reads += article.getReadsNumber();
                likes += article.getLikesNumber();
            }
            item.setReadsNumber(reads);
            item.setLikesNumber(likes);
            userMapper.updateById(item);
        }
    }

    //判断文章是否存在
    private boolean existArticle(Integer aid) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("title").lambda().eq(Article::getAuthorId, aid);
        Article article = articleMapper.selectOne(queryWrapper);
        return StringUtils.isEmpty(article);
    }
}
