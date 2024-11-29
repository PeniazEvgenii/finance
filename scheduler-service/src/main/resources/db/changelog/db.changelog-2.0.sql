--liquibase formatted sql

--changeset Evgenii:create_scheme_app
create SCHEMA IF NOT EXISTS app;

--changeset Evgenii:schedules

CREATE TABLE IF NOT EXISTS app.schedules
(
    id         UUID,
    start_time TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
    stop_time  TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
    interval   BIGINT NOT NULL,
    time_unit  VARCHAR(10) NOT NULL,
    CONSTRAINT schedules_pkey PRIMARY KEY (id)
);


--changeset Evgenii:operations

CREATE TABLE IF NOT EXISTS app.operations
(
    id          UUID,
    account_id  UUID NOT NULL,
    category_id UUID NOT NULL,
    description TEXT NOT NULL,
    value       NUMERIC NOT NULL,
    currency_id UUID NOT NULL,
    CONSTRAINT operations_pkey PRIMARY KEY (id)

);


--changeset Evgenii:scheduled_operations

CREATE TABLE IF NOT EXISTS app.scheduled_operations
(
    id             UUID,
    dt_create      TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
    dt_update      TIMESTAMP(3) WITHOUT TIME ZONE NOT NULL,
    schedule_id    UUID NOT NULL,
    operation_id   UUID NOT NULL,
    CONSTRAINT scheduled_operations_pkey PRIMARY KEY (id),
    CONSTRAINT fk_schedule_id FOREIGN KEY (schedule_id) REFERENCES app.schedules (id),
    CONSTRAINT fk_operation_id FOREIGN KEY (operation_id) REFERENCES app.operations (id)
);

