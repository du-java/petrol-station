--liquibase formatted sql
--changeset du-java:1630431191.1

CREATE TABLE IF NOT EXISTS `dispenser_petrol_list` (
  `dispenser_id` bigint NOT NULL,
  `petrol_list_id` bigint NOT NULL,
  KEY `dispenser_petrol_list_key_petrol_list_id` (`petrol_list_id`),
  KEY `dispenser_petrol_list_key_dispenser_id` (`dispenser_id`),
  CONSTRAINT `dispenser_petrol_list_fkey_petrol_list_id` FOREIGN KEY (`petrol_list_id`) REFERENCES `petrol` (`id`),
  CONSTRAINT `dispenser_petrol_list_fkey_dispenser_id` FOREIGN KEY (`dispenser_id`) REFERENCES `dispenser` (`id`)
);