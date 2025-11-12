CREATE TABLE `t_order`
(
    `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `order_number` VARCHAR(255) DEFAULT NULL,
    `sku_code` VARCHAR(255),
    `price` DECIMAL(19,2),
    `quantity` int(11)
);