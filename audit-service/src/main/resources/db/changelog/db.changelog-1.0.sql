--liquibase formatted sql

--changeset Evgenii:1
create SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.users
(
    id uuid,
    mail character varying NOT NULL,
    fio character varying NOT NULL,
    role character varying NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;

--changeset Evgenii:2
CREATE TABLE IF NOT EXISTS app.audits
(
    id uuid,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    user_id uuid NOT NULL,
    text character varying NOT NULL,
    type character varying NOT NULL,
    essence_id character varying NOT NULL,
    CONSTRAINT audits_pkey PRIMARY KEY (id),
    CONSTRAINT fk_users_id FOREIGN KEY (user_id) REFERENCES app.users (id)
);

ALTER TABLE IF EXISTS app.audits
    OWNER to postgres;
