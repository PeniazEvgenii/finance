--liquibase formatted sql

--changeset Evgenii:1
CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.accounts
(
    id uuid,
    title character varying NOT NULL,
    description character varying NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    balance numeric NOT NULL,
    type character varying NOT NULL,
    currency_id uuid,
    user_id uuid,
    CONSTRAINT accounts_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.accounts
    OWNER to postgres;


--changeset Evgenii:2
CREATE TABLE IF NOT EXISTS app.operations
(
    id uuid,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    date timestamp(3) without time zone NOT NULL,
    description character varying NOT NULL,
    category_id uuid NOT NULL,
    value numeric NOT NULL,
    account_id uuid NOT NULL,
    CONSTRAINT operations_pkey PRIMARY KEY (id),
    CONSTRAINT fk_operations FOREIGN KEY (account_id) REFERENCES app.accounts (id)
);

ALTER TABLE IF EXISTS app.operations
    OWNER to postgres;