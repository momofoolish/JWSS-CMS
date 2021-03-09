create table tb_menu
(
    id        int auto_increment
        primary key,
    col_name  varchar(16)  null comment '菜单名称',
    col_type  int          null comment '菜单类型,1在左,0在右',
    col_url   varchar(128) null comment '菜单对应路径',
    col_state int          null comment '菜单状态，1为已登录，0为未登录'
)
    collate = utf8_bin;

INSERT INTO JwssNet.tb_menu (id, col_name, col_type, col_url, col_state) VALUES (1, '首页', 1, '/', 0);
INSERT INTO JwssNet.tb_menu (id, col_name, col_type, col_url, col_state) VALUES (2, '写文章', 0, '/author/editor', 1);
INSERT INTO JwssNet.tb_menu (id, col_name, col_type, col_url, col_state) VALUES (3, '登录', 0, '/login', 0);
INSERT INTO JwssNet.tb_menu (id, col_name, col_type, col_url, col_state) VALUES (4, '注册', 0, '/register', 0);