--liquibase formatted sql
--changeset du-java:1630431025.1

CREATE TABLE IF NOT EXISTS `petrol` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  PRIMARY KEY (`id`)
);
