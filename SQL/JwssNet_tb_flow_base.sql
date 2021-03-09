create table tb_flow_base
(
    id               int auto_increment comment '自增id'
        primary key,
    title            varchar(255) collate utf8_bin not null comment '流程标题',
    content          varchar(512) collate utf8_bin null comment '流程内容填写',
    opinion          varchar(255) collate utf8_bin null comment '审批意见',
    audit_state      int unsigned                  null comment '审核结果，0审核中，1通过，2退回，3归档',
    create_date      datetime                      null comment '创建日期',
    current_operator varchar(36) collate utf8_bin  not null comment '当前操作人',
    flow_id          int                           not null comment '流程ID',
    constraint flowName
        foreign key (flow_id) references tb_workflow (id),
    constraint flowuser
        foreign key (current_operator) references tb_user (id)
);

INSERT INTO JwssNet.tb_flow_base (id, title, content, opinion, audit_state, create_date, current_operator, flow_id) VALUES (1, '标题测试1', '内容测试1', '意见测试1', 1, '2021-01-11 22:05:19', 'a6d795c1ad0f4f12b3f146e7e343298f', 1);
INSERT INTO JwssNet.tb_flow_base (id, title, content, opinion, audit_state, create_date, current_operator, flow_id) VALUES (8, '标题测试2', '内容测试2', '意见测试2', 1, '2021-01-11 22:05:19', 'a6d795c1ad0f4f12b3f146e7e343298f', 1);
INSERT INTO JwssNet.tb_flow_base (id, title, content, opinion, audit_state, create_date, current_operator, flow_id) VALUES (9, '标题测试3', '内容测试3', '意见测试3', 0, '2020-03-11 22:05:19', 'a6d795c1ad0f4f12b3f146e7e343298f', 1);
INSERT INTO JwssNet.tb_flow_base (id, title, content, opinion, audit_state, create_date, current_operator, flow_id) VALUES (10, '标题测试4', '内容测试4', '意见测试4', 2, '2021-01-11 22:01:19', 'a6d795c1ad0f4f12b3f146e7e343298f', 1);
INSERT INTO JwssNet.tb_flow_base (id, title, content, opinion, audit_state, create_date, current_operator, flow_id) VALUES (11, '标题测试5', '内容测试5', '意见测试5', 0, '2021-01-03 20:05:19', 'a6d795c1ad0f4f12b3f146e7e343298f', 1);