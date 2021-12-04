-- Database: `ccc_shop`
CREATE DATABASE IF NOT EXISTS ccc_shop;
USE ccc_shop;

-- Table structure for table `user`
CREATE TABLE IF NOT EXISTS `user`
(
    `id`          int(10) PRIMARY KEY AUTO_INCREMENT,
    `username`    varchar(255) NOT NULL,
    `identity`    varchar(30)  NOT NULL,
    `password`    varchar(255) NOT NULL,
    `phone`       varchar(30)  NOT NULL,
    `email`       varchar(255) NOT NULL,
    `credit_card` varchar(20)  DEFAULT NULL,
    `address`     varchar(255) DEFAULT NULL
);

-- Table structure for table `product`
CREATE TABLE IF NOT EXISTS `product`
(
    `id`                int(10) PRIMARY KEY AUTO_INCREMENT,
    `vender_id`         int(10)      NOT NULL,
    `name`              varchar(255) NOT NULL,
    `category`          varchar(255) NOT NULL,
    `price`             int(20)      NOT NULL,
    `stock`             int(6)       NOT NULL,
    `warehouse_address` varchar(100) NOT NULL,
    `description`       varchar(500) DEFAULT NULL,
    `pictureURL`        varchar(500) DEFAULT NULL,
    FOREIGN KEY (vender_id) REFERENCES `user` (`id`)
);

-- Table structure for table `shipping_discount`
CREATE TABLE IF NOT EXISTS `shipping_discount`
(
    `discount_code`      int(10) PRIMARY KEY AUTO_INCREMENT,
    `vender_id`          int(10)      NOT NULL,
    `policy_description` varchar(500) NOT NULL,
    `start_time`         TIMESTAMP    NOT NULL,
    `end_time`           TIMESTAMP    NOT NULL,
    `target_price`       int(20)      NOT NULL,
    FOREIGN KEY (vender_id) REFERENCES `user` (`id`)
);

-- Table structure for table `seasonings_discount`
CREATE TABLE IF NOT EXISTS `seasonings_discount`
(
    `discount_code`      int(10) PRIMARY KEY AUTO_INCREMENT,
    `vender_id`          int(10)      NOT NULL,
    `policy_description` varchar(500) NOT NULL,
    `start_time`         TIMESTAMP    NOT NULL,
    `end_time`           TIMESTAMP    NOT NULL,
    `discount_rate`      double       NOT NULL,
    FOREIGN KEY (vender_id) REFERENCES `user` (`id`)
);

-- Table structure for table `special_discount`
CREATE TABLE IF NOT EXISTS `special_discount`
(
    `discount_code`      int(10) PRIMARY KEY AUTO_INCREMENT,
    `product_id`         int(10)      NOT NULL,
    `vender_id`          int(10)      NOT NULL,
    `policy_description` varchar(500) NOT NULL,
    `start_time`         TIMESTAMP    NOT NULL,
    `end_time`           TIMESTAMP    NOT NULL,
    `category`           varchar(255) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES `product` (`id`),
    FOREIGN KEY (vender_id) REFERENCES `user` (`id`)
);

-- Table structure for table `order`
CREATE TABLE IF NOT EXISTS `order`
(
    `id`                      int(10) PRIMARY KEY AUTO_INCREMENT,
    `customer_id`             int(10)      NOT NULL,
    `shipping_fee`            int(5)       NOT NULL,
    `recipient_name`          varchar(20)  NOT NULL,
    `shipping_address`        varchar(255) NOT NULL,
    `status`                  varchar(20)  NOT NULL,
    `payment_method`          varchar(20)  NOT NULL,
    `credit_card_id`          varchar(20)           DEFAULT NULL,
    `order_time`              TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `shipping_time`           TIMESTAMP    NULL     DEFAULT NULL,
    `delivery_time`           TIMESTAMP    NULL     DEFAULT NULL,
    `seasoning_discount_code` int(6)                DEFAULT NULL,
    `shipping_discount_code`  int(6)                DEFAULT NULL,
    FOREIGN KEY (seasoning_discount_code) REFERENCES `seasonings_discount` (`discount_code`) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (shipping_discount_code) REFERENCES `shipping_discount` (`discount_code`) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (customer_id) REFERENCES `user` (`id`)
);

-- Table structure for table `order_items`
CREATE TABLE IF NOT EXISTS `order_items`
(
    `order_id`   int(10),
    `product_id` int(10),
    `quantity`   int(6) NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES `order` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES `product` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Table structure for table `shopping_cart`
CREATE TABLE IF NOT EXISTS `shopping_cart`
(
    `product_id`  int(10),
    `customer_id` int(10),
    `quantity`    int(6) NOT NULL,
    PRIMARY KEY (product_id, customer_id),
    FOREIGN KEY (product_id) REFERENCES `product` (`id`),
    FOREIGN KEY (customer_id) REFERENCES `user` (`id`)
);

-- Table structure for table `manage_order`
CREATE TABLE IF NOT EXISTS `manage_order`
(
    `order_id`  int(10),
    `vender_id` int(10),
    PRIMARY KEY (order_id, vender_id),
    FOREIGN KEY (order_id) REFERENCES `order` (`id`),
    FOREIGN KEY (vender_id) REFERENCES `user` (`id`)
);

-- Table structure for table `valuation`
CREATE TABLE IF NOT EXISTS `valuation`
(
    `customer_id` int(10),
    `product_id`  int(10),
    `comment`     varchar(500) NOT NULL,
    `rating`      int(1)       NOT NULL,
    PRIMARY KEY (customer_id, product_id),
    FOREIGN KEY (customer_id) REFERENCES `user` (`id`)
);