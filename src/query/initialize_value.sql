USE ccc_shop;

INSERT `user` (`username`, `identity`, `password`, `phone`, `email`, `credit_card`, `address`)
VALUES ('Admin', 'ADMIN', 'Admin123', '0912341234', 'admin@gmail.com', '0000111-2222333', 'home'),
       ('Apple', 'STAFF', 'Apple123', '0912345678', 'apple@gmail.com', '1111222-3333444', '台北市大安區忠孝東路xxx號5F'),
       ('ASUS', 'STAFF', 'ASUS123', '0900112233', 'google@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
       ('Samsung', 'STAFF', 'Samsung123', '0909090909', 'acer@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
       ('Zachary', 'CUSTOMER', 'Zachary123', '0943214321', 'zachary@gmail.com', '2222333-4444555', '台北市大安區忠孝東路xxx號5F'),
       ('Mandy', 'CUSTOMER', 'Mandy123', '0987654321', 'mandy@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
       ('Patrick', 'CUSTOMER', 'Patrick123', '0943214321', 'patrick@gmail.com', '1122333-4444555', '台北市大安區忠孝東路xxx號5F'),
       ('Sandy', 'CUSTOMER', 'Sandy123', '0943214321', 'sandy@gmail.com', '8822333-4444555', '台北市大安區忠孝東路xxx號5F'),
       ('David', 'CUSTOMER', 'David123', '0987654321', 'david@gmail.com', NULL, '台北市大安區忠孝東路xxx號5F'),
       ('Teddy', 'CUSTOMER', 'Teddy123', '0987654321', 'teddy@gmail.com', '8822333-4444555', '台北市大安區忠孝東路xxx號5F');

INSERT `product` (`vender_id`, `name`, `category`, `price`, `stock`, `warehouse_address`, `description`, `pictureURL`, `exist_flag`)
VALUES (2, 'Macbook Pro', 'NOTEBOOK', 54700, 152, 'XX市XX區XX路XX號XX樓', '這是很厲害的蘋果筆電', 'https://attach.setn.com/newsimages/2021/10/19/3367537-PH.jpg', true),
       (2, 'iMac', 'COMPUTER', 72900, 48, 'XX市XX區XX路XX號XX樓', '這是很厲害的蘋果電腦', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/imac-og-202008?wid=600&hei=315&fmt=jpeg&qlt=95&.v=1594849639000', true),
       (2, 'iPhone 13 Pro', 'PHONE', 32900, 200, 'XX市XX區XX路XX號XX樓', '這是很貴的蘋果手機', 'https://web-eshop.cdn.hinet.net/eShop%20Images/Product/phones/000100004254/154632-C50321583002.jpg', true),
       (2, 'iPad mini', 'TABLET', 14900, 124, 'XX市XX區XX路XX號XX樓', '這是很好用的蘋果平板', 'https://store.storeimages.cdn-apple.com/8756/as-images.apple.com/is/ipad-mini-select-202109_FMT_WHH?wid=2000&hei=2000&fmt=jpeg&qlt=80&.v=1631751019000', true),
       (2, 'iPhone SE', 'PHONE', 14500, 37, 'XX市XX區XX路XX號XX樓', '這是比較便宜的蘋果手機', 'https://www.apple.com/newsroom/images/product/iphone/standard/Apple_new-iphone-se-white_04152020_big.jpg.large.jpg', true),
       (3, 'ChromePad CT100', 'TABLET', 9500, 23, 'XX市XX區XX路XX號XX樓', '這是很好用的華碩平板', 'https://www.asus.com/media/global/gallery/MxNQtIeWKR9EANan_setting_xxx_0_90_end_2000.png', true),
       (3, 'Zenfone 8', 'PHONE', 21900, 276, 'XX市XX區XX路XX號XX樓', '這是比較便宜的華碩手機', 'https://dlcdnwebimgs.asus.com/gain/c05b6491-6d2b-48f0-81f0-d268840208d6/', true),
       (3, 'ROG Phone 5s Pro', 'PHONE', 37990, 120, 'XX市XX區XX路XX號XX樓', '這是很貴的華碩手機', 'https://dlcdnwebimgs.asus.com/gain/885B6C14-4A95-44AE-9073-602451A79511/w1000/h732', true),
       (3, 'TUF Dash F15', 'NOTEBOOK', 45000, 326, 'XX市XX區XX路XX號XX樓', '這是很好用的華碩筆電', 'https://dlcdnwebimgs.asus.com/gain/15d0f62e-8b6b-4acd-b938-1bfbd908cb67/', false),
       (3, 'S700TA', 'COMPUTER', 27000, 8, 'XX市XX區XX路XX號XX樓', '這是很厲害的華碩電腦', 'https://www.asus.com/media/global/gallery/spghwb4k7042hglq_setting_xxx_0_90_end_2000.png', true),
       (4, 'Galaxy Book Pro 360', 'NOTEBOOK', 12000, 28, 'XX市XX區XX路XX號XX樓', '這是很好用的三星筆電', 'https://images.samsung.com/is/image/samsung/p6pim/uk/feature/155011270/uk-feature-thin-as-a-smartphone--powerful-as-a-pc-505622468?$FB_TYPE_A_MO_JPG$', true),
       (4, 'Galaxy Z Fold3 5G', 'PHONE', 56888, 380, 'XX市XX區XX路XX號XX樓', '這是很貴的三星手機', 'https://images.samsung.com/ca/smartphones/galaxy-z-fold3-5g/buy/zfold3_carousel_productimage_phantomsilver_mo.jpg?imwidth=720', true),
       (4, 'Galaxy Tab A7', 'TABLET', 8490, 230, 'XX市XX區XX路XX號XX樓', '這是比較便宜的三星平板', 'https://images.samsung.com/is/image/samsung/tw-galaxy-tab-a7-t500-sm-t500nzaebri-frontgray-319595330?$720_576_PNG$', true),
       (4, 'Galaxy Note20 5G', 'PHONE', 32900, 54, 'XX市XX區XX路XX號XX樓', '這是比較便宜的三星手機', 'https://images.samsung.com/is/image/samsung/tw/galaxy-note20/gallery/tw-galaxy-note20-5g-n981-sm-n9810zagbri-frontmysticgray-thumb-272461109', true),
       (4, 'Galaxy Tab S7+ 5G', 'TABLET', 34990, 28, 'XX市XX區XX路XX號XX樓', '這是很貴的三星平板', 'https://images.samsung.com/is/image/samsung/p5/tw/tablets/galaxy-tab-s7/images/galaxy-tab-s7-s7plus-keyboard-spen-mystic-bronze-mo.jpg', true);

INSERT `shipping_discount` (`vender_id`, `policy_description`, `start_time`, `end_time`, `target_price`)
VALUES (2, '2021/12/18當日結帳金額超過10000免運費', '2021-12-18 00:00:00', '2021-12-18 23:59:59', 10000),
       (3, '雙十一結帳金額超過15000免運', '2021-11-07 00:00:00', '2021-11-13 23:59:59', 15000),
       (4, '聖誕節結帳金額超過30000運費折抵', '2021-12-20 00:00:00', '2021-12-25 23:59:59', 30000),
       (3, '新年到！ ASUS全館商品滿58888免運！', '2021-12-29 00:00:00', '2022-01-31 23:59:59', 58888);

INSERT `seasonings_discount` (`vender_id`, `policy_description`, `start_time`, `end_time`, `discount_rate`)
VALUES (2, '開學季全面9折', '2020-08-01 00:00:00', '2020-09-30 23:59:59', 0.9),
       (3, '春節特賣全商品享79折優惠', '2021-02-01 00:00:00', '2021-02-28 23:59:59', 0.79),
       (4, '母親節活動全店88折', '2021-05-01 00:00:00', '2021-05-31 23:59:59', 0.88),
       (3, '聖誕節活動全商品79折優惠', '2021-12-24 00:00:00', '2021-12-25 23:59:59', 0.79),
       (3, '新年買起來！虎年1月Samsung全系列商品85折優惠！', '2022-01-01 00:00:00', '2022-01-31 23:59:59', 0.9),
       (4, 'ASUS全館7折優惠！', '2022-12-29 00:00:00', '2022-01-14 23:59:59', 0.7);

INSERT `special_discount` (`vender_id`, `policy_description`, `start_time`, `end_time`, `category`, `discount_rate`)
VALUES (2, 'iPhone 13 Pro特惠出清', '2021-08-01 00:00:00', '2021-09-30 23:59:59', 'PHONE', 0.9),
       (3, 'TUF Dash F15折扣', '2021-02-01 00:00:00', '2021-02-28 23:59:59', 'NOTEBOOK', 0.8),
       (4, 'Galaxy Z Fold3 5G上市優惠', '2021-05-01 00:00:00', '2021-05-31 23:59:59', 'TABLET', 0.75),
       (3, 'ASUS手機 全面9折', '2021-12-29 00:00:00', '2022-01-31 23:59:59', 'PHONE', 0.9),
       (2, '新年特惠！Apple平板全面9折！', '2021-12-29 00:00:00', '2022-01-31 23:59:59', 'TABLET', 0.9);

INSERT `order` (`customer_id`, `shipping_fee`, `recipient_name`, `shipping_address`, `status`, `payment_method`, `credit_card_id`, `order_time`, `shipping_time`, `delivery_time`, `seasoning_discount_code`, `shipping_discount_code`, `total_price`)
VALUES (6, 100, 'Mandy', '台北市大安區忠孝東路xxx號5F', 'RECEIVED', 'MOBILE', NULL, '2020-09-21 12:34:56', '2020-09-23 12:34:56', '2020-09-25 12:34:56', 1, NULL, 19810),
       (5, 500, 'Zachary', '台北市大安區忠孝東路xxx號5F', 'RECEIVED', 'CASH', NULL, '2021-11-01 12:34:56', '2021-11-02 12:34:56', '2021-11-05 12:34:56', NULL, NULL, 17480),
       (6, 120, 'Sandy', '台北市大安區忠孝東路xxx號5F', 'DELIVERED', 'CREDIT_CARD', '9999888-7777666', '2021-11-25 12:34:56', '2021-11-27 12:34:56', '2021-11-30 12:34:56', NULL, NULL, 45120),
       (5, 300, 'Zack', '台北市大安區忠孝東路xxx號5F', 'SHIPPING', 'CREDIT_CARD', '2222333-4444555', '2021-12-19 12:34:56', '2021-12-20 12:34:56', NULL, NULL, NULL, 15200),
       (5, 0, 'Zachary', '台北市大安區忠孝東路xxx號5F', 'ORDER', 'CASH', NULL, '2021-12-25 12:34:56', NULL, NULL, NULL, 3, 36000),
       (5, 300, 'ZackZack', '台北市大安區忠孝東路xxx號5F', 'ORDER', 'CASH', NULL, '2021-12-28 12:34:56', NULL, NULL, NULL, NULL, 106100),
       (7, 1000, 'Doraemon', '台北市大安區忠孝東路xxx號5F', 'ORDER', 'CREDIT_CARD', "8888888-8888888", '2021-11-20 12:34:56', NULL, NULL, NULL, NULL, 205800);

INSERT `order_items` (`order_id`, `product_id`, `quantity`)
VALUES (1, 7, 1),
       (1, 8, 3),
       (1, 10, 2),
       (2, 13, 2),
       (2, 12, 1),
       (3, 9, 1),
       (4, 4, 1),
       (5, 11, 3),
       (6, 3, 1),
       (6, 2, 1),
       (6, 1, 1),
       (7, 1, 1),
       (7, 2, 1),
       (7, 3, 1),
       (7, 4, 2),
       (7, 5, 1);

INSERT `shopping_cart` (`product_id`, `customer_id`, `quantity`)
VALUES (3, 5, 3),
       (8, 6, 1),
       (4, 5, 5),
       (6, 5, 2),
       (7, 5, 4),
       (15, 5, 1),
       (12, 5, 1);

INSERT `manage_order` (`order_id`, `vender_id`)
VALUES (1, 3),
       (2, 4),
       (3, 3),
       (4, 2),
       (5, 4),
       (6, 2),
       (7, 2);

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
