--liquibase formatted sql

--changeset Evgenii:1
ALTER TABLE IF EXISTS app.operations
    ADD COLUMN version INT default 0;

--changeset Evgenii:2
ALTER TABLE IF EXISTS app.accounts
    ADD COLUMN version INT default 0;

