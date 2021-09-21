--liquibase formatted sql
--changeset du-java:1631814709.1

ALTER TABLE petrol_db.`order` CHANGE count quantity NUMERIC NULL;
ALTER TABLE petrol_db.`order` MODIFY COLUMN quantity NUMERIC NULL;

--changeset du-java:1631814709.2

ALTER TABLE petrol_db.`order` ADD user_id BIGINT NULL;