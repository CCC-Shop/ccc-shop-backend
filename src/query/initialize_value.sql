USE ccc_shop;

INSERT `user` (`username`, `identity`, `password`,`phone`, `email`, `credit_card`, `address`) VALUES
('admin', 'admin', 'admin','0912312345', 'admin@gmail.com', '0000111-2222333', 'home'),
('staff1', 'staff', 'staff1', '0912345678', 'staff1@gmail.com', '1111222-3333444', '台北市大安區忠孝東路xxx號5F'),
('staff2', 'staff', 'staff2', '0966477382', 'staff2@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
('customer1', 'customer', 'customer1', '0918537624', 'customer1@gmail.com', '2222333-4444555', '台北市大安區忠孝東路xxx號5F'),
('customer2', 'customer', 'customer2', '0987654321', 'customer2@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F');

INSERT `product` (`vendor_id`, `name`, `category`, `price`, `stock`, `warehouse_address`, `description`, `pictureURL`) VALUES
(2, 'computer1', 'COMPUTER', 50000, 1000, 'XX市XX區XX路XX號XX樓', 'one of computer', 'https://xxx.xxx'),
(2, 'notebook1', 'NOTEBOOK', 30000, 300, 'XX市XX區XX路XX號OO樓', 'one of notebook', 'https://xxx.xxo'),
(3, 'tablet1', 'TABLET', 8000, 150, 'XX市XX區XX路OO號XX樓', 'one of tablet', 'https://xxx.xox'),
(3, 'phone1', 'PHONE', 10000, 20, 'XX市XX區OO路XX號XX樓', 'one of phone', 'https://xxx.oxx'),
(3, 'computer2', 'COMPUTER', 60000, 50, 'XX市OO區XX路XX號XX樓', 'one of computerXDDDD', 'https://xxo.xxx');

INSERT `order` (`customer_id`, `shipping_fee`, `recipient_name`, `shipping_address`, `status`, `payment_method`, `credit_card_id`, `order_time`, `shipping_time`, `delivery_time`, `seasoning_discount_code`, `shipping_discount_code`) VALUES
(4, 200, 'customer1', '台北市大安區忠孝東路xxx號5F', 'ORDER', 'CASH', NULL, '2021-11-24 20:34:30', '2021-11-25 20:34:30', '2021-11-26 20:34:30', NULL, NULL),
(4, 100, 'customer?', '台北市大安區忠孝東路OOO號5F', 'SHIPPING', 'CREDIT_CARD', '2222333-4444555', '2021-11-26 20:34:30', NULL, NULL, 1, NULL),
(5, 300, 'customer2', '台北市大安區忠孝東路xxx號5F', 'DELIVERED', 'CASH', '3333444-5555666', '2021-11-24 20:34:30', '2021-11-25 20:34:30', '2021-11-26 20:34:30', NULL, 1),
(5, 80, 'customer2', '台北市大安區忠孝東路xxx號5F', 'RECEIVED', 'CREDIT_CARD', '3333444-5555666', '2021-11-25 20:34:30', '2021-11-26 20:34:30', NULL, 1, NULL),
(5, 250, 'customer?', '台北市大安區忠孝東路OOO號5F', 'ORDER', 'MOBILE', NULL, '2021-11-26 20:34:30', NULL, NULL, NULL, 1);

INSERT `order_items` (`order_id`, `product_id`, `quantity`) VALUES
(1, 5, 2),
(2, 4, 10),
(3, 5, 1),
(4, 2, 5),
(5, 1, 3);

INSERT `shopping_cart` (`product_id`, `customer_id`, `quantity`) VALUES
(2, 4, 3),
(3, 5, 6),
(5, 5, 10),
(1, 4, 1),
(4, 5, 5);

INSERT `manage_order` (`order_id`, `vendor_id`) VALUES
(1, 3),
(2, 3),
(3, 3),
(4, 2),
(5, 2);

INSERT `valuation` (`customer_id`, `product_id`, `comment`, `rating`) VALUES
(4, 1, 'good', 4),
(5, 2, 'normal', 3),
(5, 5, 'bad', 1),
(4, 3, 'nice', 5),
(5, 4, 'well', 2);

