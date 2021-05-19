package com.jwss.cms;

import com.jwss.cms.service.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CMSApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ArticleService articleService;

    @Test
    void contextLoads() {
        // logger.info(articleService.count() + "");
    }
}
