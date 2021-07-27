--liquibase formatted sql
--changeset ctran79:20210725-1400-add-order-table.sql

create table orders
(
    id          serial primary key,
    client      varchar(255),
    address     varchar(1023),
    create_date timestamp default current_timestamp
);

create table order_items
(
    id           serial primary key,
    order_id     int not null references orders (id),
    seq_no       int not null,
    product_id   int not null references products (id),
    product_name varchar(255),
    note         varchar(1023)
)