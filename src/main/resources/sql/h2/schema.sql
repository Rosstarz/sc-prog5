drop table if exists games;

create table if not exists games
(
    id             bigint not null
    constraint game_pkey
    primary key auto_increment,
    developer_id   bigint,
    owned          boolean,
    owned_store_id bigint,
    price          double precision,
    release_date   date,
    title          varchar(255)
    );

-- alter table games
--     owner to postgres;
