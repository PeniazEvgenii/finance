\c mail

CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION user_app_notification;

GRANT USAGE,CREATE ON SCHEMA app TO user_app_notification;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA app to user_app_notification;