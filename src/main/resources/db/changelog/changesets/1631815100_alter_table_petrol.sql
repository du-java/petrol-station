--liquibase formatted sql
--changeset du-java:1631815156.1

ALTER TABLE petrol_db.petrol ADD CONSTRAINT petrol_un UNIQUE KEY (name);
