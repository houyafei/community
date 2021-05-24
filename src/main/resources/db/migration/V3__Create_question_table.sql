-- auto-generated definition
create table if not exists question
(
    id            INTEGER
        primary key autoincrement,
    title         varchar(100),
    description   text,
    gmt_create    bitint,
    gmt_modified  bitint,
    creator       int,
    comment_count int default 0,
    view_count    int default 0,
    like_count    int default 0,
    tag           varchar(256)
);
