package com.nhnacademy.springjpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/*
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
 */
@NamedEntityGraphs({
        @NamedEntityGraph(name = "orderDetailWithOrderAndProduct", attributeNodes = {
                @NamedAttributeNode("product"),
                @NamedAttributeNode(value = "order", subgraph = "orderWithUser")
        }, subgraphs = @NamedSubgraph(name = "orderWithUser", attributeNodes = {
                @NamedAttributeNode("user")
        }))
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @Column(name = "order_details_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailsId;
    @Column(name = "order_id", insertable = false, updatable = false)
    private Integer orderId;
    @Column(name = "product_id", insertable = false, updatable = false)
    private Integer productId;
    @Column(name = "order_detail_quantity", nullable = false)
    private Integer orderDetailQuantity;
    @Column(name = "product_unit_cost", nullable = false)
    private BigDecimal productUnitCost;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_short_desc")
    private String productShortDesc;
    @Column(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}