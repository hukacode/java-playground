create table address
(
    id                 uuid                                  not null,
    floor              varchar,
    street             varchar,
    number             varchar,
    postal_code        integer,
    country_code       varchar,
    country            varchar,
    created_by         varchar,
    updated_by         varchar,
    created_at         timestamptz default CURRENT_TIMESTAMP not null,
    updated_at         timestamptz default CURRENT_TIMESTAMP not null,
    constraint pk_address primary key (id)
);

create table user_profile
(
    id              uuid                                  not null,
    avatar          varchar,
    phone_number    varchar,
    first_name      varchar,
    last_name       varchar,
    email           varchar,
    system_language varchar,
    native_language varchar,
    gender          varchar,
    date_of_birth   date,
    address_id      uuid,
    created_by      varchar,
    updated_by      varchar,
    created_at      timestamptz default CURRENT_TIMESTAMP not null,
    updated_at      timestamptz default CURRENT_TIMESTAMP not null,
    constraint pk_user_profile primary key (id),
    constraint fk_address foreign key (address_id) references address (id)
);