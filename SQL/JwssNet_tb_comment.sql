create table tb_comment
(
    id         int          not null comment 'ID'
        primary key,
    articleId  varchar(42)  null comment '文章ID',
    userId     varchar(32)  null comment '用户ID',
    content    varchar(512) null comment '内容',
    createDate datetime     null comment '创建日期'
)
    collate = utf8_bin;

