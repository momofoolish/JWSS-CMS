create table tb_friends
(
    id    int auto_increment
        primary key,
    url   varchar(512) null comment '友链地址',
    name  varchar(128) null comment '友链名称',
    state int          null comment '状态：1传递权重，0不传递权重'
)
    collate = utf8_bin;

INSERT INTO JwssNet.tb_friends (id, url, name, state) VALUES (1, 'www.vjwss.top', 'Jwss博客', 1);
INSERT INTO JwssNet.tb_friends (id, url, name, state) VALUES (2, 'http://devonte.top/', 'Devonte''s Blockhouse', 1);