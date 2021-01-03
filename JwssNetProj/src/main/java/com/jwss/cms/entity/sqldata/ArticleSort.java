package com.jwss.cms.entity.sqldata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_article_sort")
public class ArticleSort {
    int id;
    String name;
}
