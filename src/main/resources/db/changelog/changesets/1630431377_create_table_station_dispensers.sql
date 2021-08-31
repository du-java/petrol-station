--liquibase formatted sql
--changeset du-java:1630431377.1

CREATE TABLE IF NOT EXISTS `station_dispensers` (
  `station_id` bigint NOT NULL,
  `dispensers_id` bigint NOT NULL,
  UNIQUE KEY `station_dispensers_ukey_dispensers_id` (`dispensers_id`),
  KEY `station_dispensers_key_station_id` (`station_id`),
  CONSTRAINT `station_dispensers_fkey_dispensers_id` FOREIGN KEY (`dispensers_id`) REFERENCES `dispenser` (`id`),
  CONSTRAINT `station_dispensers_fkey_station_id` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`)
);
