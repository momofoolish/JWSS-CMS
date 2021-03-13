package com.jwss.cms;

import com.jwss.cms.model.article.TbArticle;
import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.util.SqlUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class CMSApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    ArticleService articleService;

    @Test
    void contextLoads() {

    }
}
