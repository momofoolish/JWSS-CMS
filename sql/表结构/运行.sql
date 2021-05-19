create table if not exists tb_article
(
	id varchar(42) not null comment 'ID'
		primary key,
	author_id varchar(32) null comment '作者id',
	title varchar(128) null comment '标题',
	description varchar(255) null comment '描述',
	content text null comment '内容',
	cover varchar(512) null comment '封面',
	sort_id int null comment '分类id',
	alter_date datetime null comment '修改日期',
	create_date datetime not null comment '创建日期',
	likes_number int null comment '点赞数量',
	reads_number int null comment '阅读数量',
	comment_number int null comment '评论数量',
	state int null comment '文章状态，0审核，1通过，2退回，3保存，-1锁定',
	label varchar(128) null comment '标签 '
)
collate=utf8_bin;

create table if not exists tb_article_sort
(
	id int auto_increment
		primary key,
	name varchar(16) not null comment '分类名称'
)
collate=utf8_bin;

create table if not exists tb_comment
(
	id int not null comment 'ID'
		primary key,
	articleId varchar(42) null comment '文章ID',
	userId varchar(32) null comment '用户ID',
	content varchar(512) null comment '内容',
	createDate datetime null comment '创建日期'
)
collate=utf8_bin;

create table if not exists tb_cron
(
	id int auto_increment
		primary key,
	cron varchar(32) not null comment '调度规则
',
	job_name varchar(128) null comment '任务名称',
	remark varchar(255) null comment '任务描述',
	state int null comment '调度状态：1开启，0关闭'
);

create table if not exists tb_feedback
(
	id int auto_increment
		primary key,
	user_id varchar(32) null comment '用户id',
	content varchar(512) null comment '内容',
	create_date datetime null comment '创建时间'
)
collate=utf8_bin;

create table if not exists tb_files
(
	id int null,
	name varchar(64) null comment '文件名称',
	parent_id int null comment '文件父级',
	state int null comment '文件状态,1表示可用',
	file_type varchar(16) null comment '文件类型',
	upload_date int null comment '上传日期',
	file_size int null comment '文件大小'
);

create table if not exists tb_friends
(
	id int auto_increment
		primary key,
	url varchar(512) null comment '友链地址',
	name varchar(128) null comment '友链名称',
	state int null comment '状态：1传递权重，0不传递权重'
)
collate=utf8_bin;

create table if not exists tb_menu
(
	id int auto_increment
		primary key,
	col_name varchar(16) null comment '菜单名称',
	col_type int null comment '菜单类型,0在最左,依次递增',
	col_url varchar(128) null comment '菜单对应路径',
	col_state int null comment '优先级，越往右值越大'
)
collate=utf8_bin;

create table if not exists tb_user
(
	id varchar(36) not null comment 'UUID'
		primary key,
	account varchar(32) null comment '账号',
	password varchar(32) null comment '密码',
	roles varchar(32) null comment '角色',
	name varchar(32) null comment '昵称',
	sign varchar(255) null comment '签名',
	mail varchar(64) null comment '邮箱',
	avatar varchar(512) null comment '头像',
	reads_number int null comment '阅读总量',
	likes_number int null comment '点赞总量',
	register_date datetime null comment '注册日期'
)
collate=utf8_bin;

create table if not exists tb_workflow
(
	id int not null
		primary key,
	name varchar(128) null
);

create table if not exists tb_flow_base
(
	id int auto_increment comment '自增id'
		primary key,
	title varchar(255) collate utf8_bin not null comment '流程标题',
	content varchar(512) collate utf8_bin null comment '流程内容填写',
	opinion varchar(255) collate utf8_bin null comment '审批意见',
	audit_state int unsigned null comment '审核结果，0审核中，1通过，2退回，3归档',
	create_date datetime null comment '创建日期',
	current_operator varchar(36) collate utf8_bin not null comment '当前操作人',
	flow_id int not null comment '流程ID',
	constraint flowName
		foreign key (flow_id) references tb_workflow (id),
	constraint flowuser
		foreign key (current_operator) references tb_user (id)
);

