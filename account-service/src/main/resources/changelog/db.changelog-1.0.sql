--liquibase formatted sql

--changeset Evgenii:1
CREATE SCHEMA IF NOT EXISTS account;
--     AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS account.accounts
(
    id uuid,
    title character varying NOT NULL,
    description character varying NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    balance numeric NOT NULL,
    type character varying NOT NULL,
    currency_id uuid,
    user_id uuid,
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS account.accounts
    OWNER to postgres;

--changeset Evgenii:2
CREATE TABLE IF NOT EXISTS classifier.operation_categories
(
    id uuid,
    title character varying NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    CONSTRAINT currencies_pkey PRIMARY KEY (id),
    CONSTRAINT title_category_unique UNIQUE (title)
);

ALTER TABLE IF EXISTS classifier.operation_categories
    OWNER to postgres;