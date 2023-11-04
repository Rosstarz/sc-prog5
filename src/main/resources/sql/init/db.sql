create table stores
(
    id                   serial
        constraint stores_pk
            primary key,
    name                 varchar(30),
    has_openable_library boolean,
    link_to_library      varchar(255)
);

create table developers
(
    id         serial
        constraint developers_pk
            primary key,
    name       varchar(30) not null,
    country_id varchar(64)
        constraint developers_country_id_fk
            references countries
            on update cascade
);

create table public.games
(
    id             serial
        constraint games_pk
            primary key,
    title          varchar(30)           not null,
    release_date   timestamp,
    price          double precision      not null,
    developer_id   integer               not null
        constraint games_developers_id_fk
            references public.developers
            on update cascade,
    owned          boolean default false not null,
    owned_store_id integer
        constraint games_stores_id_fk
            references public.stores
            on update cascade,
    image          varchar(255)
);

create table publishments
(
    store_id integer not null
        constraint publishments_stores_id_fk
            references stores
            on update cascade,
    game_id  integer not null
        constraint publishments_games_id_fk
            references games
            on update cascade on delete cascade,
    constraint publishments_pk
        primary key (store_id, game_id)
);

