CREATE TABLE app.mails
(
    id uuid,
    mail character varying NOT NULL,
    code character varying NOT NULL,
    fio character varying,
    title character varying NOT NULL,
    status character varying NOT NULL,
    version bigint NOT NULL,
    dt_create timestamp(3) with time zone NOT NULL,
    dt_update timestamp(3) with time zone NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS app.mails
    OWNER to user_app_notification;