--liquibase formatted sql

--changeset Evgenii:1
create SCHEMA IF NOT EXISTS app
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS app.currencies
(
    id uuid,
    title character varying NOT NULL,
    description character varying NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    CONSTRAINT currencies_pkey PRIMARY KEY (id),
    CONSTRAINT title_currency_unique UNIQUE (title)
);

ALTER TABLE IF EXISTS app.currencies
    OWNER to postgres;

--changeset Evgenii:2
CREATE TABLE IF NOT EXISTS app.operation_categories
(
    id uuid,
    title character varying NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL,
    CONSTRAINT operation_categories_pkey PRIMARY KEY (id),
    CONSTRAINT title_category_unique UNIQUE (title)
);

ALTER TABLE IF EXISTS app.operation_categories
    OWNER to postgres;