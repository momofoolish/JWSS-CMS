package com.jwss.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwss.blog.entity.sqldata.Article;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

}