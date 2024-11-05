--liquibase formatted sql

--changeset Evgenii:1
create SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.users
(
    id uuid NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    mail character varying NOT NULL,
    password character varying NOT NULL,
    fio character varying NOT NULL,
    role character varying NOT NULL,
    status character varying NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT mail_unique UNIQUE (mail)
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;

--changeset Evgenii:2
CREATE TABLE app.codes
(
    id bigserial,
    user_id uuid NOT NULL,
    code character varying NOT NULL,
    dt_create  timestamp without time zone NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES app.users (id)
);

ALTER TABLE IF EXISTS app.codes
    OWNER to postgres;