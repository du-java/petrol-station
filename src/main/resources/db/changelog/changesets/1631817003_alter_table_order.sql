--liquibase formatted sql
--changeset du-java:1631817003.1

RENAME TABLE petrol_db.`order` TO petrol_db.orders;

