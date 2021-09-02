--liquibase formatted sql
--changeset du-java:1630431085.1

CREATE TABLE IF NOT EXISTS `tank` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` decimal(19,2) NOT NULL,
  `petrol_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tank_key` (`petrol_id`),
  CONSTRAINT `tank_fkey_petrol_id` FOREIGN KEY (`petrol_id`) REFERENCES `petrol` (`id`)
);
