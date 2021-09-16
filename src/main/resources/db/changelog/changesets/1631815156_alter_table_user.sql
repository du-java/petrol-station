--liquibase formatted sql
--changeset du-java:1631815100.1

ALTER TABLE petrol_db.`user` ADD CONSTRAINT user_un UNIQUE KEY (name);
