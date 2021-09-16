--liquibase formatted sql
--changeset du-java:1630430890.1
CREATE TABLE IF NOT EXISTS 'station' (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);
