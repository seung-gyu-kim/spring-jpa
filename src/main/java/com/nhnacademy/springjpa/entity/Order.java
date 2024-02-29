package com.nhnacademy.springjpa.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
CREATE TABLE `orders` (
                          `order_id`	int	NOT NULL AUTO_INCREMENT	COMMENT '주문 번호',
                          `user_id`	varchar(50)	NULL	COMMENT '유저 아이디, ON DELETE SET NULL ON UPDATE CASCADE',
                          `order_date`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP	COMMENT '주문일자',
                          `order_ship_date`	datetime	NULL	COMMENT '배송일자',
                          `order_address`	varchar(300)	NOT NULL	COMMENT '배송 주소',
                          PRIMARY KEY (`order_id`),
                          CONSTRAINT `fk_orders_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주문';
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(name = "user_id", insertable = false, updatable = false)
    private String userId;
    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;
    @Column(name = "order_ship_date")
    private LocalDateTime orderShipDate;
    @Column(name = "order_address", nullable = false)
    private String orderAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}