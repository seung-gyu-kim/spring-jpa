package com.nhnacademy.springjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/*
CREATE TABLE `category_product_registrations` (
                                                  `product_id`	int	NOT NULL	COMMENT '상품 아이디',
                                                  `category_id`	INT	NOT NULL	COMMENT '카테고리 아이디',
                                                  PRIMARY KEY (`product_id`, `category_id`),
                                                  CONSTRAINT `fk_category_product_registrations_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                                                  CONSTRAINT `fk_category_product_registrations_category_id` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리_상품_등록';
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "category_product_registrations")
@IdClass(CategoryProductRegistration.PK.class)
public class CategoryProductRegistration {
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Id
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @MapsId("categoryId")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter@Setter
    @EqualsAndHashCode
    public static class PK implements Serializable {
        private Integer productId;
        private Integer categoryId;
    }
}