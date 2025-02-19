FROM postgres:17-alpine

ADD sql/001_database.sql /docker-entrypoint-initdb.d/
ADD account-service/sql/002_ddl.sql /docker-entrypoint-initdb.d/
ADD audit-service/sql/003_ddl.sql /docker-entrypoint-initdb.d/
ADD classifier-service/sql/004_ddl.sql /docker-entrypoint-initdb.d/
ADD user-service/sql/005_ddl.sql /docker-entrypoint-initdb.d/
ADD scheduler-service/sql/006_ddl.sql /docker-entrypoint-initdb.d/
ADD notification-service/sql/007_ddl.sql /docker-entrypoint-initdb.d/