create table country
(
    id int auto_increment,
    name varchar(255) not null,
    code varchar(255) not null,
    constraint country_pk
        primary key (id)
);

create table city
(
    id int auto_increment,
    name varchar(255) not null,
    country_id int not null,
    constraint city_pk
        primary key (id),
    constraint country__fk
        foreign key (country_id) references country (id)
            on update cascade on delete cascade
);
