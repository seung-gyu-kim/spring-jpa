DROP TABLE IF EXISTS category_product_registrations;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS order_details;
DROP TABLE IF EXISTS shopping_carts;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS points;
DROP TABLE IF EXISTS users;

CREATE TABLE `users` (
    `user_id` varchar(50) NOT NULL,
    `user_name` varchar(50) NOT NULL,
    `user_password` varchar(200) NOT NULL,
    `user_birth` varchar(8) NOT NULL,
    `user_auth` varchar(10) NOT NULL CHECK (`user_auth` IN ('ROLE_ADMIN', 'ROLE_USER')),
    `user_point` int NOT NULL DEFAULT 1000000,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `latest_login_at` timestamp,
    PRIMARY KEY (`user_id`)
);

CREATE INDEX `users_created_at_index` ON `users`(`created_at` DESC);
CREATE INDEX `users_user_auth_index` ON `users`(`user_auth`);

CREATE TABLE `points` (
    `point_id` int AUTO_INCREMENT,
    `user_id` varchar(50) NOT NULL,
    `point_value` int NOT NULL,
    `point_type` varchar(30) CHECK (`point_type` IN ('POINT_JOIN', 'POINT_PURCHASE', 'POINT_DAILY_LOGIN', 'POINT_SAVE', 'POINT_SAVE_CANCEL')),
    `point_desc` text,
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`point_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `addresses` (
    `user_id` varchar(50) NOT NULL,
    `address` varchar(255) NOT NULL,
    `is_default` boolean NOT NULL DEFAULT FALSE,
    PRIMARY KEY (`user_id`, `address`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `orders` (
    `order_id` int AUTO_INCREMENT,
    `user_id` varchar(50),
    `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `order_ship_date` timestamp,
    `order_address` varchar(300) NOT NULL,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `products` (
    `product_id` int AUTO_INCREMENT,
    `product_name` varchar(120) NOT NULL,
    `product_unit_cost` decimal(15) NOT NULL,
    `product_short_desc` varchar(300),
    `product_desc` text,
    `product_quantity` int NOT NULL,
    `detail_image_url` varchar(2083),
    `thumbnail_image_url` varchar(2083),
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `shopping_carts` (
    `product_id` int NOT NULL,
    `user_id` varchar(50) NOT NULL,
    `cart_quantity` int NOT NULL CHECK (`cart_quantity` >= 1),
    PRIMARY KEY (`product_id`, `user_id`),
    FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `order_details` (
    `order_details_id` int AUTO_INCREMENT,
    `order_id` int NOT NULL,
    `product_id` int,
    `order_detail_quantity` int NOT NULL CHECK (`order_detail_quantity` >= 1),
    `product_unit_cost` decimal(15) NOT NULL,
    `product_name` varchar(120) NOT NULL,
    `product_short_desc` varchar(300),
    `thumbnail_image_url` varchar(2083),
    PRIMARY KEY (`order_details_id`),
    FOREIGN KEY (`order_id`) REFERENCES `orders`(`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE `categories` (
    `category_id` int AUTO_INCREMENT,
    `category_name` varchar(50) NOT NULL,
    PRIMARY KEY (`category_id`)
);


CREATE TABLE `category_product_registrations` (
    `product_id` int NOT NULL,
    `category_id` int NOT NULL,
    PRIMARY KEY (`product_id`, `category_id`),
    FOREIGN KEY (`product_id`) REFERENCES `products`(`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`category_id`) REFERENCES `categories`(`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO users SET user_id = 'admin', user_name = '관리자', user_password = '12345', user_birth = '19960826', user_auth = 'ROLE_ADMIN', user_point = 100000000, created_at = '2024-02-02 00:00:00';
INSERT INTO users SET user_id = 'user', user_name = '회원', user_password = '12345', user_birth = '19960826', user_auth = 'ROLE_USER', user_point = 100000000, created_at = '2024-02-02 00:00:00';

INSERT INTO points SET user_id = 'user', point_value = 1000000, point_type = 'POINT_JOIN', point_desc = '가입 축하 포인트', created_at = '2024-02-02 00:00:00';

INSERT INTO addresses SET user_id = 'user', address = '서울 송파구 올림픽로 300', is_default = 1;

INSERT INTO orders SET user_id = 'user', order_date = '2024-02-02 00:00:00', order_ship_date = '2024-02-03 07:00:00', order_address = '서울 송파구 올림픽로 300';

INSERT INTO products SET product_name = '맥북 프로', product_unit_cost = 3500000, product_short_desc = '소개', product_desc = '설명', product_quantity = 100, detail_image_url = 'resources/details/1.jpg', thumbnail_image_url = 'resources/thumbnails/1.jpg';
INSERT INTO products SET product_name = '갤럭시 s24', product_unit_cost = 1259560, product_short_desc = '소개', product_desc = '설명', product_quantity = 100, detail_image_url = 'resources/details/2.jpg', thumbnail_image_url = 'resources/thumbnails/2.jpg';

INSERT INTO shopping_carts SET product_id = 1, user_id = 'user', cart_quantity = 1;

INSERT INTO order_details SET order_id = 1, product_id = 1, order_detail_quantity = 1, product_unit_cost = 3500000, product_name = '맥북 프로', product_short_desc = '소개', thumbnail_image_url = 'resources/thumbnails/1.jpg';

INSERT INTO categories SET category_name = '전자제품';
INSERT INTO categories SET category_name = '휴대용품';

INSERT INTO category_product_registrations SET product_id = 1, category_id = 1;
INSERT INTO category_product_registrations SET product_id = 1, category_id = 2;
