--liquibase formatted sql

--changeset Evgenii:1
ALTER TABLE IF EXISTS app.users
    ADD COLUMN version BIGINT default 0;
