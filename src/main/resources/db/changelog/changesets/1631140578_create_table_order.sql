--liquibase formatted sql
--changeset du-java:1631140578.1

CREATE TABLE `order`
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    amount    DECIMAL               NULL,
    count     INT                   NULL,
    petrol_id BIGINT                NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

--changeset du-java:1631140578.2
ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_PETROL FOREIGN KEY (petrol_id) REFERENCES petrol (id);
