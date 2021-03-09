create table tb_cron
(
    id       int auto_increment
        primary key,
    cron     varchar(32)  not null comment '调度规则
',
    job_name varchar(128) null comment '任务名称',
    remark   varchar(255) null comment '任务描述',
    state    int          null comment '调度状态：1开启，0关闭'
)
    charset = utf8;

INSERT INTO JwssNet.tb_cron (id, cron, job_name, remark, state) VALUES (1, '0/5 * * * * ? *', '测试', '用来测试的', 1);