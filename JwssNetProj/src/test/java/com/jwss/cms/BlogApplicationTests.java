package com.jwss.cms;

import com.jwss.cms.config.HostConfig;
import com.jwss.cms.mapper.ArticleMapper;
import com.jwss.cms.service.article.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BlogApplicationTests {
    @Resource
    HostConfig hostConfig;
    @Resource
    ArticleMapper articleMapper;
    @Resource
    ArticleServiceImpl articleServiceImpl;

    @Test
    void contextLoads() {

    }
}
