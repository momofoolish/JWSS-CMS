package com.jwss.cms.entity.sqldata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tb_article")
public class Article {
    private String id;
    private int sortId;     //分类id
    private int state;      //文章状态，0审核，1通过，-1锁定
    private String authorId;
    private String title;
    private String description;
    private String content;
    private String label;
    private String cover;
    private Date alterDate;
    private Date createDate;
    private int likesNumber;    //文章点赞量
    private int readsNumber;    //文章阅读量
    private int commentNumber;  //文章评论数
}
