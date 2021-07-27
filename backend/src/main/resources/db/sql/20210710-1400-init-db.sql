--liquibase formatted sql
--changeset ctran79:20210710-1400-init-db.sql

create table products
(
    id   serial primary key,
    code varchar(255),
    name varchar(1023),
    note varchar(1023)
)