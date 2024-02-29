-- INSERT INTO users SET user_id = 'admin', user_name = '관리자', user_password = '12345', user_birth = '19960826', user_auth = 'ROLE_ADMIN', user_point = 100000000, created_at = '2024-02-02 00:00:00';
-- INSERT INTO users SET user_id = 'user', user_name = '회원', user_password = '12345', user_birth = '19960826', user_auth = 'ROLE_USER', user_point = 100000000, created_at = '2024-02-02 00:00:00';
--
-- INSERT INTO points SET user_id = 'user', point_value = 1000000, point_type = 'POINT_JOIN', point_desc = '가입 축하 포인트', created_at = '2024-02-02 00:00:00';
--
-- INSERT INTO addresses SET user_id = 'user', address = '서울 송파구 올림픽로 300', is_default = 1;
--
-- INSERT INTO orders SET user_id = 'user', order_date = '2024-02-02 00:00:00', order_ship_date = '2024-02-03 07:00:00', order_address = '서울 송파구 올림픽로 300';
--
-- INSERT INTO products SET product_name = '맥북 프로', product_unit_cost = 3500000, product_short_desc = '소개', product_desc = '설명', product_quantity = 100, detail_image_url = 'resources/details/1.jpg', thumbnail_image_url = 'resources/thumbnails/1.jpg';
--
-- INSERT INTO shopping_carts SET product_id = 1, user_id = 'user', cart_quantity = 1;
--
-- INSERT INTO order_details SET order_id = 1, product_id = 1, order_detail_quantity = 1, product_unit_cost = 3500000, product_name = '맥북 프로', product_short_desc = '소개', thumbnail_image_url = 'resources/thumbnails/1.jpg';
--
-- INSERT INTO categories SET category_name = '노트북';
--
-- INSERT INTO category_product_registrations SET product_id = 1, category_id = 1;

DROP TABLE IF EXISTS `category_product_registrations`;
DROP TABLE IF EXISTS `categories`;
DROP TABLE IF EXISTS `order_details`;
DROP TABLE IF EXISTS `shopping_carts`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `addresses`;
DROP TABLE IF EXISTS `points`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `user_id` varchar(50) NOT NULL COMMENT '아이디',
                         `user_name` varchar(50) NOT NULL COMMENT '이름',
                         `user_password` varchar(200) NOT NULL COMMENT 'mysql password 사용',
                         `user_birth` varchar(8) NOT NULL COMMENT '생년월일 : 19840503',
                         `user_auth` varchar(10) NOT NULL CHECK (`user_auth` IN ('ROLE_ADMIN', 'ROLE_USER')) COMMENT '권한: ROLE_ADMIN,ROLE_USER',
                         `user_point` int NOT NULL DEFAULT 1000000 COMMENT 'default : 1000000',
                         `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '가입일자',
                         `latest_login_at` datetime DEFAULT NULL COMMENT '마지막 로그인 일자',
                         KEY `users_created_at_index` (`created_at` DESC),
                         KEY `users_user_auth_index` (`user_auth`),
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원';

INSERT INTO users SET user_id = 'admin', user_name = '관리자', user_password = '12345', user_birth = '19960826', user_auth = 'ROLE_ADMIN', user_point = 100000000, created_at = '2024-02-02 00:00:00';
INSERT INTO users SET user_id = 'user', user_name = '회원', user_password = '12345', user_birth = '19960826', user_auth = 'ROLE_USER', user_point = 100000000, created_at = '2024-02-02 00:00:00';

CREATE TABLE `points` (
                          `point_id`	int	NOT NULL AUTO_INCREMENT	COMMENT '포인트 아이디',
                          `user_id`	varchar(50)	NOT NULL	COMMENT '유저 아이디, ON DELETE CASCADE ON UPDATE CASCADE',
                          `point_value`	int	NOT NULL	COMMENT '포인트 값',
                          `point_type`	varchar(30)	NULL CHECK (
                              `point_type` IN (
                                               'POINT_JOIN',
                                               'POINT_PURCHASE',
                                               'POINT_DAILY_LOGIN',
                                               'POINT_SAVE',
                                               'POINT_SAVE_CANCEL'
                                  )
                              ) COMMENT '포인트 타입: POINT_JOIN, POINT_PURCHASE, POINT_REFUND, POINT_DAILY_LOGIN, POINT_SAVE, POINT_SAVE_CANCEL',
                          `point_desc`	text	NULL	COMMENT '포인트 부가 설명(필요시 특이사항 작성)',
                          `created_at`	datetime	NOT NULL    default CURRENT_TIMESTAMP	COMMENT '포인트 변동 일자',
                          PRIMARY KEY (`point_id`),
                          CONSTRAINT `fk_points_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='포인트';

INSERT INTO points SET user_id = 'user', point_value = 1000000, point_type = 'POINT_JOIN', point_desc = '가입 축하 포인트', created_at = '2024-02-02 00:00:00';

CREATE TABLE `addresses` (
                             `user_id`	varchar(50)	NOT NULL	COMMENT '유저 아이디, ON DELETE CASCADE ON UPDATE CASCADE',
                             `address`	varchar(255)	NOT NULL	COMMENT '주소',
                             `is_default`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT '기본 주소 여부',
                             PRIMARY KEY (`user_id`, `address`),
                             CONSTRAINT `fk_addresses_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주소';

INSERT INTO addresses SET user_id = 'user', address = '서울 송파구 올림픽로 300', is_default = 1;

CREATE TABLE `orders` (
                          `order_id`	int	NOT NULL AUTO_INCREMENT	COMMENT '주문 번호',
                          `user_id`	varchar(50)	NULL	COMMENT '유저 아이디, ON DELETE SET NULL ON UPDATE CASCADE',
                          `order_date`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '주문일자',
                          `order_ship_date`	datetime	NULL	COMMENT '배송일자',
                          `order_address`	varchar(300)	NOT NULL	COMMENT '배송 주소',
                          PRIMARY KEY (`order_id`),
                          CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문';

INSERT INTO orders SET user_id = 'user', order_date = '2024-02-02 00:00:00', order_ship_date = '2024-02-03 07:00:00', order_address = '서울 송파구 올림픽로 300';

CREATE TABLE `products` (
                            `product_id`	int	NOT NULL AUTO_INCREMENT	COMMENT '상품 아이디',
                            `product_name`	varchar(120)	NOT NULL	COMMENT '상품 이름',
                            `product_unit_cost`	decimal(15)	NOT NULL	COMMENT '상품 개별 가격',
                            `product_short_desc`	varchar(300)	NULL	COMMENT '상품 한 줄 설명',
                            `product_desc`	text	NULL	COMMENT '상품 설명',
                            `product_quantity`	int	NOT NULL	COMMENT '상품 재고 수량',
                            `detail_image_url`	varchar(2083)	NULL	COMMENT '상세 페이지 이미지 주소',
                            `thumbnail_image_url`	varchar(2083)	NULL	COMMENT '썸네일 이미지 주소',
                            PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='상품';

INSERT INTO products SET product_name = '맥북 프로', product_unit_cost = 3500000, product_short_desc = '소개', product_desc = '설명', product_quantity = 100, detail_image_url = 'resources/details/1.jpg', thumbnail_image_url = 'resources/thumbnails/1.jpg';

CREATE TABLE `shopping_carts` (
                                  `product_id`	int	NOT NULL    AUTO_INCREMENT	COMMENT '상품 아이디, ON DELETE CASCADE ON UPDATE CASCADE',
                                  `user_id`	varchar(50)	NOT NULL	COMMENT '유저 아이디, ON DELETE CASCADE ON UPDATE CASCADE',
                                  `cart_quantity`	int	NOT NULL	CHECK (`cart_quantity` >= 1)  COMMENT '수량',
                                  PRIMARY KEY (`product_id`, `user_id`),
                                  CONSTRAINT `fk_shopping_cart_details_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT `fk_shopping_cart_details_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니';

INSERT INTO shopping_carts SET product_id = 1, user_id = 'user', cart_quantity = 1;

CREATE TABLE `order_details` (
                                 `order_details_id`	int	NOT NULL    AUTO_INCREMENT  COMMENT '주문 세부사항 아이디',
                                 `order_id`	int	NOT NULL	COMMENT '주문 아이디, ON DELETE CASCADE ON UPDATE CASCADE',
                                 `product_id`	int	NULL	COMMENT '상품 아이디, ON DELETE SET NULL ON UPDATE CASCADE',
                                 `order_detail_quantity`	int	NOT NULL    CHECK (`order_detail_quantity` >= 1)	COMMENT '구매 수량: 1이상',
                                 `product_unit_cost`	decimal(15)	NOT NULL	COMMENT '주문 당시 상품 개별 가격',
                                 `product_name`	varchar(120)	NOT NULL	COMMENT '주문 당시 상품명',
                                 `product_short_desc`	varchar(300)	NULL	COMMENT '주문 당시 상품 한 줄 설명',
                                 `thumbnail_image_url`	varchar(2083)	NULL	COMMENT '주문 당시 썸네일 이미지 주소',
                                 PRIMARY KEY (`order_details_id`),
                                 CONSTRAINT `fk_order_details_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                 CONSTRAINT `fk_order_details_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문 세부사항';

INSERT INTO order_details SET order_id = 1, product_id = 1, order_detail_quantity = 1, product_unit_cost = 3500000, product_name = '맥북 프로', product_short_desc = '소개', thumbnail_image_url = 'resources/thumbnails/1.jpg';

CREATE TABLE `categories` (
                              `category_id`	INT	NOT NULL    AUTO_INCREMENT  COMMENT '카테고리 아이디',
                              `category_name`	varchar(50)	NOT NULL	COMMENT '카테고리명',
                              PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리';

INSERT INTO categories SET category_name = '노트북';

CREATE TABLE `category_product_registrations` (
                                                  `product_id`	int	NOT NULL	COMMENT '상품 아이디',
                                                  `category_id`	INT	NOT NULL	COMMENT '카테고리 아이디',
                                                  PRIMARY KEY (`product_id`, `category_id`),
                                                  CONSTRAINT `fk_category_product_registrations_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                                  CONSTRAINT `fk_category_product_registrations_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리_상품_등록';

INSERT INTO category_product_registrations SET product_id = 1, category_id = 1;