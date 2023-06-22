create table if not exists auto_user (
    id        serial primary key,
    login     varchar unique not null,
    password  varchar        not null
    );


create table if not exists auto_post (
    id serial primary key,
    description  varchar,
    created timestamp not null,
    auto_user_id    int references auto_user (id) not null
    );
