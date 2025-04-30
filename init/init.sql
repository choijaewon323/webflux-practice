drop table if exists boards;
drop table if exists categories;
drop table if exists likes;
drop table if exists users;
drop table if exists replies;

create table boards (
    id serial primary key,
    title varchar,
    content varchar,
    user_id bigint,
    category_id bigint,
    cnt bigint,
    created_at timestamp,
    updated_at timestamp
);

create table categories (
    id serial primary key,
    name varchar
);

create table likes (
    id serial primary key,
    like_type varchar,
    user_id bigint,
    target_id bigint
);

create table users (
    id serial primary key,
    nickname varchar,
    email varchar,
    password varchar,
    created_at timestamp
);

create table replies (
    id serial primary key,
    user_id bigint,
    board_id bigint,
    content varchar,
    created_at timestamp,
    updated_at timestamp
);