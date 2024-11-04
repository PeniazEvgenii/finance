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
CREATE TABLE IF NOT EXISTS account.operations
(
    id uuid,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    date timestamp without time zone NOT NULL,
    description character varying NOT NULL,
    category_id uuid NOT NULL,
    value numeric NOT NULL,
    account_id uuid NOT NULL,
    CONSTRAINT operations_pkey PRIMARY KEY (id),
    CONSTRAINT fk_operations FOREIGN KEY (account_id) REFERENCES account.accounts (id)
);

ALTER TABLE IF EXISTS classifier.operation_categories
    OWNER to postgres;