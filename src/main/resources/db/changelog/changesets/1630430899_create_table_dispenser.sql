--liquibase formatted sql
--changeset du-java:1630430899.1

CREATE TABLE IF NOT EXISTS `dispenser` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);