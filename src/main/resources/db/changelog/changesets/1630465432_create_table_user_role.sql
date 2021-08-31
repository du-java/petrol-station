--liquibase formatted sql
--changeset du-java:1630465432.1

CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `user_role_key_user_id` (`user_id`),
  CONSTRAINT `user_role_fkey_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);
