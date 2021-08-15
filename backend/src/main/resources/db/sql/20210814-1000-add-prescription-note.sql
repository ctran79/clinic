--liquibase formatted sql
--changeset ctran79:20210814-1000-add-prescription-note.sql

alter table prescriptions add column if not exists note varchar(4095);