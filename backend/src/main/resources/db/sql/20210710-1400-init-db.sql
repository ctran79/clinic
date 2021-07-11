--liquibase formatted sql
--changeset ctran79:20210710-1400-init-db.sql

create table products (
    id serial,
    code varchar(255),
    name varchar(1023)
)