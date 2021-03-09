create table tb_feedback
(
    id          int auto_increment
        primary key,
    user_id     varchar(32)  null comment '用户id',
    content     varchar(512) null comment '内容',
    create_date datetime     null comment '创建时间'
)
    collate = utf8_bin;

INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (1, 'c6ce9fb9f19d4f10bdcd6ab2131cfcb7', '【全球最大的政治经济类中文评论门户网站《中国评论网》-《评论网》以“关注社会现实', '2020-12-04 00:22:08');
INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (2, 'c6ce9fb9f19d4f10bdcd6ab2131cfcb7', '博客需要评论,企业网站需要留言板,资讯网站需要评论区,社交网站更离不开交流互动,“人气”非常重要。', '2020-07-16 00:22:20');
INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (3, 'c6ce9fb9f19d4f10bdcd6ab2131cfcb7', '博客需要评论,企业网站需要留言板,资讯网站需要评论区,社交网站更离不开交流互动,“人气”非常重要。', '2020-12-03 00:22:17');
INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (4, 'c6ce9fb9f19d4f10bdcd6ab2131cfcb7', '博客需要评论,企业网站需要留言板,资讯网站需要评论区,社交网站更离不开交流互动,“人气”非常重要。', '2020-12-01 00:22:14');
INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (5, '1324370502017376257', '博客需要评论,企业网站需要留言板,资讯网站需要评论区,社交网站更离不开交流互动,“人气”非常重要。', '2019-05-06 00:22:25');
INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (6, '1324370502017376257', '博客需要评论,企业网站需要留言板,资讯网站需要评论区,社交网站更离不开交流互动,“人气”非常重要。', '2020-11-12 00:22:31');
INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (7, '1324370502017376257', '博客需要评论,企业网站需要留言板,资讯网站需要评论区,社交网站更离不开交流互动,“人气”非常重要。', '2020-12-04 00:22:35');
INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (8, '1324370502017376257', '博客需要评论,企业网站需要留言板,资讯网站需要评论区,社交网站更离不开交流互动,“人气”非常重要。', '2020-12-06 00:22:40');
INSERT INTO JwssNet.tb_feedback (id, user_id, content, create_date) VALUES (9, '1324370502017376257', '【全球最大的政治经济类中文评论门户网站《中国评论网》-《评论网》以“关注社会现实', '2019-09-06 00:22:42');