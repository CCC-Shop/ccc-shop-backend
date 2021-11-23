-- Database: `ccc_shop`
CREATE DATABASE IF NOT EXISTS ccc_shop;
USE ccc_shop;

-- Table structure for table `user`
CREATE TABLE IF NOT EXISTS `user` (
    `id` int(10) PRIMARY KEY AUTO_INCREMENT,
    `username` varchar(255) NOT NULL,
    `identity` varchar(30) NOT NULL,
    `password` varchar(255) NOT NULL,
    `phone` varchar(30) NOT NULL,
    `email` varchar(100) NOT NULL,
    `credit_card` varchar(20) DEFAULT NULL,
    `address` varchar(255) DEFAULT NULL
#     CONSTRAINT `username_UNIQUE` UNIQUE (`username`)
);

-- Table structure for table `product`
CREATE TABLE IF NOT EXISTS `product` (
    `id` int(10) PRIMARY KEY AUTO_INCREMENT,
    `user_id` int(10) NOT NULL,
    `name` varchar(255) NOT NULL,
    `category` varchar(255) NOT NULL,
    `price` int(20) NOT NULL,
    `stock` int(6) NOT NULL,
    `warehouse_address` varchar(100) NOT NULL,
    `description` varchar(500) DEFAULT NULL,
    `pictureURL` varchar(500) DEFAULT NULL,
    CONSTRAINT `user_id` FOREIGN KEY (user_id) REFERENCES `user` (`id`)
);

-- Table structure for table `discount`
--# CREATE TABLE IF NOT EXISTS `discount` (
--#     `id` int(6) NOT NULL AUTO_INCREMENT,
--#     `value` float NOT NULL,
--#     `code` varchar(5) NOT NULL,
--#     `name` varchar(100) NOT NULL,
--#     `startDate` datetime NOT NULL,
--#     `endDate` datetime NOT NULL,
--#     PRIMARY KEY (`id`),
--#     UNIQUE KEY `id_UNIQUE` (`id`),
--#     UNIQUE KEY `code_UNIQUE` (`code`)
--# );
--
-- -- Table structure for table `like`
-- CREATE TABLE IF NOT EXISTS `like` (
--  `userID` int(6) NOT NULL,
--  `itemID` int(6) NOT NULL,
--  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`),
--  CONSTRAINT `itemID` FOREIGN KEY (`itemID`) REFERENCES `item` (`id`)
-- );

 -- Table structure for table `order`
 CREATE TABLE IF NOT EXISTS `order` (
  `id`               int(10)     PRIMARY KEY AUTO_INCREMENT,
  `customer_id`      int(10)     NOT NULL,
  `shipping_fee`     int(5)      NOT NULL,
  `recipient_name`   varchar(20) NOT NULL,
  `shipping_address` varchar(255)NOT NULL,
  `status`           varchar(20) NOT NULL,
  `payment_method`   varchar(20) NOT NULL,
  `credit_card_id`   varchar(20) DEFAULT NULL,
  `order_time`       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `shipping_time`    TIMESTAMP   NULL ON UPDATE CURRENT_TIMESTAMP,
  `delivery_time`    TIMESTAMP   NULL ON UPDATE CURRENT_TIMESTAMP,
  `seasoning_discount_code` int(6)    DEFAULT NULL,
  `shipping_discount_code`  int(6)    DEFAULT NULL
--  CONSTRAINT `discountID` FOREIGN KEY (`discountID`) REFERENCES `discount` (`id`),
--  CONSTRAINT `orderUserID` FOREIGN KEY (`orderUserID`) REFERENCES `user` (`id`)
 );

 -- Table structure for table `order_items`
 CREATE TABLE IF NOT EXISTS `order_items` (
  `order_id`    int(10) NOT NULL,
  `product_id`  int(10) NOT NULL,
  `quantity`    int(6)  NOT NULL
--  CONSTRAINT `orderID` FOREIGN KEY (`orderID`) REFERENCES `order` (`id`),
--  CONSTRAINT `orderItemID` FOREIGN KEY (`orderItemID`) REFERENCES `item` (`id`)
 );
--
-- -- Table structure for table `rate`
-- CREATE TABLE IF NOT EXISTS `rate` (
--  `rateItemID` int(6) NOT NULL,
--  `rateUserID` int(6) NOT NULL,
--  `comment` varchar(100) DEFAULT NULL,
--  `rate` int(11) DEFAULT NULL,
--  CONSTRAINT `rateItemID` FOREIGN KEY (`rateItemID`) REFERENCES `item` (`id`),
--  CONSTRAINT `rateUserID` FOREIGN KEY (`rateUserID`) REFERENCES `user` (`id`)
-- );
