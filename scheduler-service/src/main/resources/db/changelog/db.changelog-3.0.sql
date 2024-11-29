--liquibase formatted sql

--changeset Evgenii:add_column_user

ALTER TABLE IF EXISTS app.scheduled_operations
    ADD COLUMN user_id uuid;



