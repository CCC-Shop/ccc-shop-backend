-- Database: `ccc_shop`
CREATE DATABASE IF NOT EXISTS ccc_shop;
USE ccc_shop;

-- Table structure for table `user`
CREATE TABLE IF NOT EXISTS `user` (
    `id` int(6) NOT NULL AUTO_INCREMENT,
    `username` varchar(30) NOT NULL,
    `identity` varchar(20) NOT NULL,
    `password` varchar(30) NOT NULL,
    `fullname` varchar(100) NOT NULL,
    `phone` int(6) NOT NULL,
    `email` varchar(100) NOT NULL,
    `credit_card` int(20),
    `address` varchar(100) NOT NULL,
    PRIMARY KEY(`id`),
    UNIQUE KEY `id_UNIQUE` (`id`),
    UNIQUE KEY `username_UNIQUE` (`username`)
);

-- Table structure for table `product`
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL,
  `category` varchar(5) NOT NULL,
  `price` int(20) NOT NULL,
  `stock` int(6) NOT NULL,
  `warehouse_address` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `pictureURL` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
--  UNIQUE KEY `id_UNIQUE` (`id`)
);

--
-- -- Table structure for table `discount`
-- CREATE TABLE IF NOT EXISTS `discount` (
--  `id` int(6) NOT NULL AUTO_INCREMENT,
--  `value` float NOT NULL,
--  `code` varchar(5) NOT NULL,
--  `name` varchar(100) NOT NULL,
--  `startDate` datetime NOT NULL,
--  `endDate` datetime NOT NULL,
--  PRIMARY KEY (`id`),
--  UNIQUE KEY `id_UNIQUE` (`id`),
--  UNIQUE KEY `code_UNIQUE` (`code`)
-- );
--
-- -- Table structure for table `like`
-- CREATE TABLE IF NOT EXISTS `like` (
--  `userID` int(6) NOT NULL,
--  `itemID` int(6) NOT NULL,
--  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`),
--  CONSTRAINT `itemID` FOREIGN KEY (`itemID`) REFERENCES `item` (`id`)
-- );
--
-- -- Table structure for table `order`
-- CREATE TABLE IF NOT EXISTS `order` (
--  `id` int(6) NOT NULL AUTO_INCREMENT,
--  `orderDate` datetime NOT NULL,
--  `status` int(11) NOT NULL,
--  `discountID` int(6) DEFAULT 1,
--  `orderUserID` int(6) NOT NULL,
--  PRIMARY KEY (`id`),
--  UNIQUE KEY `id_UNIQUE` (`id`),
--  CONSTRAINT `discountID` FOREIGN KEY (`discountID`) REFERENCES `discount` (`id`),
--  CONSTRAINT `orderUserID` FOREIGN KEY (`orderUserID`) REFERENCES `user` (`id`)
-- );
-- -- Table structure for table `itemlist`
-- CREATE TABLE IF NOT EXISTS `itemlist` (
--  `orderItemID` int(6) NOT NULL,
--  `orderID` int(6) NOT NULL,
--  `amount` int(11) NOT NULL,
--  CONSTRAINT `orderID` FOREIGN KEY (`orderID`) REFERENCES `order` (`id`),
--  CONSTRAINT `orderItemID` FOREIGN KEY (`orderItemID`) REFERENCES `item` (`id`)
-- );
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
