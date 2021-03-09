create table tb_workflow
(
    id   int          not null
        primary key,
    name varchar(128) null
);

INSERT INTO JwssNet.tb_workflow (id, name) VALUES (1, '作者权限申请流程');