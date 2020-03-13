create table question
(
	id              bigint auto_increment,
	title           varchar(50) not null,
	description     text not null,
	gmt_create      bigint not null,
	gmt_modified    bigint not null,
	creator         bigint not null,
	comment_count   int default 0,
	view_count      int default 0,
	like_count      int default 0,
	tag             varchar(256) not null,
	constraint question_pk
		primary key (id)
)character set = utf8;
