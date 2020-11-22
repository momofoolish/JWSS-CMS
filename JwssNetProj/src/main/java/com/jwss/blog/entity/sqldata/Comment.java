package com.jwss.blog.entity.sqldata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_comment")
public class Comment {
    private int id;
    private int articleId;
    private String userId;
    private String content;
    private Date createDate;
}
