--liquibase formatted sql
--changeset du-java:1631140600.1

ALTER TABLE petrol_db.`user` ADD password varchar(255) NOT NULL;
