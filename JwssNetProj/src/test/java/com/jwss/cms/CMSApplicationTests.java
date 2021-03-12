package com.jwss.cms;

import com.jwss.cms.model.article.TbArticle;
import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.util.SqlUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CMSApplicationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    ArticleService articleService;

    @Test
    void contextLoads() {
        List<TbArticle> articles = articleService.selectBySearch(1, 5, "",
                SqlUtil.colsGenerator("id", "title"));
        for (int i = 0; i < articles.size(); i++) {
            logger.info(articles.get(i).getId() + "," + articles.get(i).getTitle());
        }
    }
}
