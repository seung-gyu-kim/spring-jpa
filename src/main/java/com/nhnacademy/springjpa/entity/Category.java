package com.nhnacademy.springjpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/*
CREATE TABLE `categories` (
                              `category_id`	INT	NOT NULL    AUTO_INCREMENT  COMMENT '카테고리 아이디',
                              `category_name`	varchar(50)	NOT NULL	COMMENT '카테고리명',
                              PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='카테고리';
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "category_name", nullable = false)
    private String categoryName;
}