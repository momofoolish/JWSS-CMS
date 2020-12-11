package com.jwss.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwss.blog.entity.sqldata.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    Integer deleteBatch(@Param("idList") List<String> idList);
}
