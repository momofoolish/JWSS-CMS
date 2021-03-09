create table tb_article_sort
(
    id   int auto_increment
        primary key,
    name varchar(16) not null comment '分类名称'
)
    collate = utf8_bin;

INSERT INTO JwssNet.tb_article_sort (id, name) VALUES (1, '日常文章');
INSERT INTO JwssNet.tb_article_sort (id, name) VALUES (2, '游戏攻略');
INSERT INTO JwssNet.tb_article_sort (id, name) VALUES (3, '动漫评测');