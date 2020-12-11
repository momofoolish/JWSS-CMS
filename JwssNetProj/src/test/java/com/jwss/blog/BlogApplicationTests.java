package com.jwss.blog;

import com.jwss.blog.config.HostConfig;
import com.jwss.blog.entity.sqldata.Article;
import com.jwss.blog.mapper.ArticleMapper;
import com.jwss.blog.mapper.CountMapper;
import com.jwss.blog.service.CountService;
import com.jwss.blog.service.article.ArticleServiceImpl;
import com.jwss.blog.util.Sys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class BlogApplicationTests {
    @Resource
    HostConfig hostConfig;
    @Resource
    ArticleMapper articleMapper;

    @Test
    void contextLoads() {
        List<String> ids = new ArrayList<>();
        ids.add("111");
        ids.add("222");
        ids.add("333");
        articleMapper.deleteBatch(ids);
    }
}
