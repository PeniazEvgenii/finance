--liquibase formatted sql

--changeset 30-10-2024-Evgenii:1
ALTER TABLE IF EXISTS app.codes
    ADD COLUMN dt_create timestamp without time zone NOT NULL;


