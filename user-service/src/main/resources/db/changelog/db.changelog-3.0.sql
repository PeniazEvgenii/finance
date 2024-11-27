--liquibase formatted sql

--changeset Evgenii:initialize-admin-user
INSERT INTO app.users (id, version, dt_create, dt_update,
    mail, password, fio, role, status)
VALUES (gen_random_uuid (), 0, NOW()  AT TIME ZONE 'UTC', NOW()  AT TIME ZONE 'UTC',
           'admin@example.com',
           '$2a$10$rxKq4T9ruCph.YuXCuAjWuatDenXxNoIBgNbwfNCvYayiM9kpimlO',
           'Администратор системы', 'ADMIN','ACTIVATED'
       )
ON CONFLICT (mail) DO NOTHING;
