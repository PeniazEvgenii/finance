INSERT INTO app.users(
    id, dt_create, dt_update, mail, password, fio, role, status)
VALUES
('0ecf84a4-7c70-4b36-b3a0-f0280be8ed0c', '2024-10-31 07:49:57.06518', '2024-10-31 07:49:57.06518', 'user@example1233334.com', '1234', 'Pert', 'USER', 'WAITING_ACTIVATION'),
('3ba4f6da-2f3b-459f-83ef-52730ad3fa99', '2024-10-31 11:19:17.147304', '2024-10-31 11:36:36.506389', 'user@exampletest2.com', '1234', 'Vasia',	'USER',	'ACTIVATED'),
('85646660-1bf9-44cd-ad3d-64d9e6a963b7', '2024-10-28 13:28:34.396478', '2024-10-30 08:41:27.24932', 'user111@example.com',	'1234',	'Anna',	'MANAGER',	'ACTIVATED'),
('ebf13772-c7b2-47dd-983b-a95989372e47', '2024-11-03 17:07:45.546468', '2024-11-03 17:13:44.004444', 'string@mail',	'string', 'Ivan',	'ADMIN',	'WAITING_ACTIVATION'),
('f620ac32-5eb0-46be-b73b-61ea97f4a61c', '2024-10-30 17:51:34.094735', '2024-10-30 17:51:34.094735', 'user@example123333.com', '1234',	'Petrovich', 'USER', 'WAITING_ACTIVATION');

INSERT INTO app.codes(
    id, user_id, code, dt_create)
VALUES
(1,	(SELECT id FROM app.users WHERE mail = 'user@example1233334.com'),	'1111111',	'2024-10-30 21:04:09.429482'),
(2,	(SELECT id FROM app.users WHERE mail = 'user@example1233334.com'),	'1111111',	'2024-10-31 07:49:57.098187'),
(3,	(SELECT id FROM app.users WHERE mail = 'user@exampletest2.com'),	'5276949',	'2024-10-31 11:16:33.924399'),
(4,	(SELECT id FROM app.users WHERE mail = 'user@exampletest2.com'),	'1137187',	'2024-10-31 11:19:18.420909'),
(5,	(SELECT id FROM app.users WHERE mail = 'user111@example.com'),	'5331924',	'2024-10-31 11:45:19.359102');

SELECT SETVAL('app.codes_id_seq', (SELECT MAX(id) FROM app.codes));
