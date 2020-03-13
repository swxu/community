create table user
(
    id           bigint auto_increment,
    account_id   varchar(100) not null,
    name         varchar(20),
    avatar_url   varchar(100),
    bio          varchar(256),
    token        char(36),
    gmt_create   bigint not null,
    gmt_modified bigint not null,
    constraint user_pk
        primary key (id)
);
