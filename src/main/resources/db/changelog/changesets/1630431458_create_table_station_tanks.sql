--liquibase formatted sql
--changeset du-java:1630431458.1

CREATE TABLE IF NOT EXISTS `station_tanks` (
  `station_id` bigint NOT NULL,
  `tanks_id` bigint NOT NULL,
  UNIQUE KEY `station_tanks_ukey_tanks_id` (`tanks_id`),
  KEY `station_tanks_key_station_id` (`station_id`),
  CONSTRAINT `station_tanks_fkey_tanks_id` FOREIGN KEY (`tanks_id`) REFERENCES `tank` (`id`),
  CONSTRAINT `station_tanks_fkey_station_id` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`)
);
