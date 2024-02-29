package com.nhnacademy.springjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/*
CREATE TABLE `shopping_carts` (
                                  `product_id`	int	NOT NULL    AUTO_INCREMENT	COMMENT '상품 아이디, ON DELETE CASCADE ON UPDATE CASCADE',
                                  `user_id`	varchar(50)	NOT NULL	COMMENT '유저 아이디, ON DELETE CASCADE ON UPDATE CASCADE',
                                  `cart_quantity`	int	NOT NULL	CHECK (`cart_quantity` >= 1)  COMMENT '수량',
                                  PRIMARY KEY (`product_id`, `user_id`),
                                  CONSTRAINT `fk_shopping_cart_details_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                  CONSTRAINT `fk_shopping_cart_details_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니';
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "shopping_carts")
@IdClass(ShoppingCart.PK.class)
public class ShoppingCart {
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "cart_quantity", nullable = false)
    private Integer cartQuantity;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter@Setter
    @EqualsAndHashCode
    public static class PK implements Serializable {
        private Integer productId;
        private String userId;
    }
}