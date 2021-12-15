INSERT `user` (`username`, `identity`, `password`, `phone`, `email`, `credit_card`, `address`)
VALUES ('Admin', 'admin', 'Admin123', '0912341234', 'admin@gmail.com', '0000111-2222333', 'home'),
       ('Apple', 'staff', 'Apple123', '0912345678', 'apple@gmail.com', '1111222-3333444', '台北市大安區忠孝東路xxx號5F'),
       ('ASUS', 'staff', 'ASUS123', '0900112233', 'google@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
       ('Samsung', 'staff', 'Samsung123', '0909090909', 'acer@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
       ('Zachary', 'customer', 'Zachary123', '0943214321', 'zachary@gmail.com', '2222333-4444555', '台北市大安區忠孝東路xxx號5F'),
       ('Mandy', 'customer', 'Mandy123', '0987654321', 'mandy@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
       ('Patrick', 'customer', 'Patrick123', '0943214321', 'patrick@gmail.com', '1122333-4444555', '台北市大安區忠孝東路xxx號5F'),
       ('Sandy', 'customer', 'Sandy123', '0943214321', 'sandy@gmail.com', '8822333-4444555', '台北市大安區忠孝東路xxx號5F'),
       ('David', 'customer', 'David123', '0987654321', 'david@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
       ('Teddy', 'customer', 'Teddy123', '0987654321', 'teddy@gmail.com', '8822333-4444555', '台北市大安區忠孝東路xxx號5F');

USE ccc_shop;

INSERT `product` (`vender_id`, `name`, `category`, `price`, `stock`, `warehouse_address`, `description`, `pictureURL`)
VALUES (2, 'Macbook Pro', 'NOTEBOOK', 54700, 152, 'XX市XX區XX路XX號XX樓', '這是很厲害的蘋果筆電', 'https://attach.setn.com/newsimages/2021/10/19/3367537-PH.jpg'),
       (2, 'iMac', 'COMPUTER', 72900, 48, 'XX市XX區XX路XX號XX樓', '這是很厲害的蘋果電腦', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/imac-og-202008?wid=600&hei=315&fmt=jpeg&qlt=95&.v=1594849639000'),
       (2, 'iPhone 13 Pro', 'PHONE', 32900, 200, 'XX市XX區XX路XX號XX樓', '這是很貴的蘋果手機', 'https://web-eshop.cdn.hinet.net/eShop%20Images/Product/phones/000100004254/154632-C50321583002.jpg'),
       (2, 'iPad mini', 'TABLET', 14900, 124, 'XX市XX區XX路XX號XX樓', '這是很好用的蘋果平板', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/ipad-mini-select-202109_FMT_WHH?wid=2000&hei=2000&fmt=jpeg&qlt=80&.v=1631751019000'),
       (2, 'iPhone SE', 'PHONE', 14500, 37, 'XX市XX區XX路XX號XX樓', '這是比較便宜的蘋果手機', 'https://www.apple.com/newsroom/images/product/iphone/standard/Apple_new-iphone-se-white_04152020_big.jpg.large.jpg'),
       (3, 'ChromePad CT100', 'TABLET', 9500, 23, 'XX市XX區XX路XX號XX樓', '這是很好用的華碩平板', 'https://www.asus.com/media/global/gallery/MxNQtIeWKR9EANan_setting_xxx_0_90_end_2000.png'),
       (3, 'Zenfone 8', 'PHONE', 21900, 276, 'XX市XX區XX路XX號XX樓', '這是比較便宜的華碩手機', 'https://dlcdnwebimgs.asus.com/gain/c05b6491-6d2b-48f0-81f0-d268840208d6/'),
       (3, 'ROG Phone 5s Pro', 'PHONE', 37990, 120, 'XX市XX區XX路XX號XX樓', '這是很貴的華碩手機', 'https://dlcdnwebimgs.asus.com/gain/885B6C14-4A95-44AE-9073-602451A79511/w1000/h732'),
       (3, 'TUF Dash F15', 'NOTEBOOK', 45000, 326, 'XX市XX區XX路XX號XX樓', '這是很好用的華碩筆電', 'https://dlcdnwebimgs.asus.com/gain/15d0f62e-8b6b-4acd-b938-1bfbd908cb67/'),
       (3, 'S700TA', 'COMPUTER', 27000, 8, 'XX市XX區XX路XX號XX樓', '這是很厲害的華碩電腦', 'https://www.asus.com/media/global/gallery/spghwb4k7042hglq_setting_xxx_0_90_end_2000.png'),
       (4, 'Galaxy Book Pro 360', 'NOTEBOOK', 12000, 28, 'XX市XX區XX路XX號XX樓', '這是很好用的三星筆電', 'https://images.samsung.com/is/image/samsung/p6pim/uk/feature/155011270/uk-feature-thin-as-a-smartphone--powerful-as-a-pc-505622468?$FB_TYPE_A_MO_JPG$'),
       (4, 'Galaxy Z Fold3 5G', 'PHONE', 56888, 380, 'XX市XX區XX路XX號XX樓', '這是很貴的三星手機', 'https://images.samsung.com/ca/smartphones/galaxy-z-fold3-5g/buy/zfold3_carousel_productimage_phantomsilver_mo.jpg?imwidth=720'),
       (4, 'Galaxy Tab A7', 'TABLET', 8490, 230, 'XX市XX區XX路XX號XX樓', '這是比較便宜的三星平板', 'https://images.samsung.com/is/image/samsung/tw-galaxy-tab-a7-t500-sm-t500nzaebri-frontgray-319595330?$720_576_PNG$'),
       (4, 'Galaxy Note20 5G', 'PHONE', 32900, 54, 'XX市XX區XX路XX號XX樓', '這是比較便宜的三星手機', 'https://images.samsung.com/is/image/samsung/tw/galaxy-note20/gallery/tw-galaxy-note20-5g-n981-sm-n9810zagbri-frontmysticgray-thumb-272461109'),
       (4, 'Galaxy Tab S7+ 5G', 'TABLET', 34990, 28, 'XX市XX區XX路XX號XX樓', '這是很貴的三星平板', 'https://images.samsung.com/is/image/samsung/p5/tw/tablets/galaxy-tab-s7/images/galaxy-tab-s7-s7plus-keyboard-spen-mystic-bronze-mo.jpg');

INSERT `shipping_discount` (`vender_id`, `policy_description`, `start_time`, `end_time`, `target_price`)
VALUES (2, '2021/12/18當日結帳金額超過10000免運費', '2021-12-18 00:00:00', '2021-12-18 23:59:59', 10000),
       (3, '雙十一結帳金額超過15000免運', '2021-11-07 00:00:00', '2021-11-13 23:59:59', 15000),
       (4, '聖誕節結帳金額超過30000運費折抵', '2021-12-20 00:00:00', '2021-12-31 23:59:59', 30000);

INSERT `seasonings_discount` (`vender_id`, `policy_description`, `start_time`, `end_time`, `discount_rate`)
VALUES (2, '開學季全面9折', '2021-08-01 00:00:00', '2021-09-30 23:59:59', 0.9),
       (3, '春節特賣全商品享79折優惠', '2021-02-01 00:00:00', '2021-02-28 23:59:59', 0.79),
       (4, '母親節活動全店88折', '2021-05-01 00:00:00', '2021-05-31 23:59:59', 0.88);

INSERT `special_discount` (`product_id`, `vender_id`, `policy_description`, `start_time`, `end_time`, `category`)
VALUES (3, 2, 'iPhone 13 Pro特惠出清', '2021-08-01 00:00:00', '2021-09-30 23:59:59', 'PHONE'),
       (9, 3, 'TUF Dash F15折扣', '2021-02-01 00:00:00', '2021-02-28 23:59:59', 'NOTEBOOK'),
       (12, 4, 'Galaxy Z Fold3 5G上市優惠', '2021-05-01 00:00:00', '2021-05-31 23:59:59', 'TABLET');

INSERT `order` (`customer_id`, `shipping_fee`, `recipient_name`, `shipping_address`, `status`, `payment_method`, `credit_card_id`, `order_time`, `shipping_time`, `delivery_time`, `seasoning_discount_code`,`shipping_discount_code`)
VALUES (6, 100, 'Mandy', '台北市大安區忠孝東路xxx號5F', 'RECEIVED', 'MOBILE', NULL, '2021-09-21 12:34:56', '2021-09-23 12:34:56', '2021-09-25 12:34:56', 1, NULL),
       (5, 0, 'Zachary', '台北市大安區忠孝東路xxx號5F', 'RECEIVED', 'CASH', NULL, '2021-11-11 12:34:56', '2021-11-12 12:34:56', '2021-11-15 12:34:56', NULL, 2),
       (6, 120, 'Sandy', '台北市大安區忠孝東路xxx號5F', 'DELIVERED', 'CREDIT_CARD', '9999888-7777666', '2021-11-25 12:34:56', '2021-11-27 12:34:56', '2021-11-30 12:34:56', NULL, NULL),
       (5, 0, 'Zack', '台北市大安區忠孝東路xxx號5F', 'SHIPPING', 'CREDIT_CARD', '2222333-4444555', '2021-12-18 12:34:56', '2021-12-20 12:34:56', NULL, NULL, 1),
       (5, 0, 'Zachary', '台北市大安區忠孝東路xxx號5F', 'ORDER', 'CASH', NULL, '2021-12-25 12:34:56', NULL, NULL, NULL, 3);

INSERT `order_items` (`order_id`, `product_id`, `quantity`)
VALUES (1, 7, 1),
       (2, 13, 2),
       (3, 9, 1),
       (4, 4, 1),
       (5, 11, 3);

INSERT `shopping_cart` (`product_id`, `customer_id`, `quantity`)
VALUES (3, 5, 3),
       (8, 6, 1),
       (12, 5, 1);

INSERT `manage_order` (`order_id`, `vender_id`)
VALUES (1, 3),
       (2, 4),
       (3, 3),
       (4, 2),
       (5, 4);

INSERT `valuation` (`customer_id`, `product_id`, `comment`, `rating`)
VALUES (4, 1, 'Not really well.', 2),
       (5, 1, 'Not really well.', 3),
       (5, 2, 'Good.', 4),
       (6, 2, 'Excellent!', 5),
       (7, 4, 'Good.', 4),
       (7, 3, 'Excellent!', 5),
       (8, 4, 'Good.', 4),
       (8, 1, 'Gooood!', 4),
       (8, 5, 'Excellent!', 5),
       (9, 6, 'very good!', 5),
       (4, 10, 'very good!', 4),
       (9, 11, 'Excellent!', 5),
       (9, 12, 'so good!', 5),
       (9, 13, 'very good!', 4);
