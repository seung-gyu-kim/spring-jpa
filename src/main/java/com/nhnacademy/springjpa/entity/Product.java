package com.nhnacademy.springjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/*
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
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_unit_cost", nullable = false)
    private BigDecimal productUnitCost;
    @Column(name = "product_short_desc")
    private String productShortDesc;
    @Column(name = "product_desc")
    private String productDesc;
    @Column(name = "product_quantity", nullable = false)
    private Integer productQuantity;
    @Column(name = "detail_image_url")
    private String detailImageUrl;
    @Column(name = "thumbnail_image_url")
    private String thumbnailImageUrl;
}