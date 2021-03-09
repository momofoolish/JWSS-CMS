create table tb_user
(
    id            varchar(36)  not null comment 'UUID'
        primary key,
    account       varchar(32)  null comment '账号',
    password      varchar(32)  null comment '密码',
    roles         varchar(32)  null comment '角色',
    name          varchar(32)  null comment '昵称',
    sign          varchar(255) null comment '签名',
    mail          varchar(64)  null comment '邮箱',
    avatar        varchar(512) null comment '头像',
    reads_number  int          null comment '阅读总量',
    likes_number  int          null comment '点赞总量',
    register_date datetime     null comment '注册日期'
)
    collate = utf8_bin;

INSERT INTO JwssNet.tb_user (id, account, password, roles, name, sign, mail, avatar, reads_number, likes_number, register_date) VALUES ('0850658b8ee94713a679c207707f8ee6', 'systemAdmin', '23e5813213d0e4c6f402588b8ba7eadf', 'admin', 'systemAdmin', null, null, 'https://jwss-1257389675.cos.ap-chengdu.myqcloud.com/jwsource/imgs/bkavatar.jpg', 0, 0, '2021-01-06 16:00:39');
INSERT INTO JwssNet.tb_user (id, account, password, roles, name, sign, mail, avatar, reads_number, likes_number, register_date) VALUES ('1324370502017376257', 'zhangsan', '4297f44b13955235245b2497399d7a93', 'author', 'zhangsan', null, null, 'https://jwss-1257389675.cos.ap-chengdu.myqcloud.com/jwsource/imgs/bkavatar.jpg', 0, 0, '2020-11-05 15:18:28');
INSERT INTO JwssNet.tb_user (id, account, password, roles, name, sign, mail, avatar, reads_number, likes_number, register_date) VALUES ('486d4e94fc9f46cbac5166eb8e0263aa', 'sysadmin', '23e5813213d0e4c6f402588b8ba7eadf', 'admin', '系统管理员', null, null, 'https://jwss-1257389675.cos.ap-chengdu.myqcloud.com/jwsource/imgs/bkavatar.jpg', 0, 0, '2021-02-18 14:34:32');
INSERT INTO JwssNet.tb_user (id, account, password, roles, name, sign, mail, avatar, reads_number, likes_number, register_date) VALUES ('a6d795c1ad0f4f12b3f146e7e343298f', 'Alice go', '7abdccbea8473767e91378e37850d296', 'user', 'Alice go', null, null, 'https://jwss-1257389675.cos.ap-chengdu.myqcloud.com/jwsource/imgs/bkavatar.jpg', 0, 0, '2021-01-06 15:59:30');
INSERT INTO JwssNet.tb_user (id, account, password, roles, name, sign, mail, avatar, reads_number, likes_number, register_date) VALUES ('fe3e948ce7b74064909c104234ba65c2', 'lihua22', '4297f44b13955235245b2497399d7a93', 'author', 'lihua22', null, null, 'https://jwss-1257389675.cos.ap-chengdu.myqcloud.com/jwsource/imgs/bkavatar.jpg', 0, 0, '2020-11-14 16:45:06');