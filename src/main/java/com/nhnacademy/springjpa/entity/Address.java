package com.nhnacademy.springjpa.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/*
CREATE TABLE `addresses` (
                             `user_id`	varchar(50)	NOT NULL	COMMENT '유저 아이디, ON DELETE CASCADE ON UPDATE CASCADE',
                             `address`	varchar(255)	NOT NULL	COMMENT '주소',
                             `is_default`	tinyint(1)	NOT NULL	DEFAULT 0	COMMENT '기본 주소 여부',
                             PRIMARY KEY (`user_id`, `address`),
                             CONSTRAINT `fk_addresses_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='주소';
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "addresses")
@IdClass(Address.PK.class)
public class Address {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter@Setter
    @EqualsAndHashCode
    public static class PK implements Serializable {
        private String userId;
        private String address;
    }
}
