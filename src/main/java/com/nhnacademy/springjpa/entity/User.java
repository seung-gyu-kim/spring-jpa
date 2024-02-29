package com.nhnacademy.springjpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/*
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
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Column(name = "user_password", nullable = false)
    private String userPassword;
    @Column(name = "user_birth", nullable = false)
    private String userBirth;
    @Column(name = "user_auth", nullable = false)
    private String userAuth;
    @Column(name = "user_point", nullable = false)
    private Integer userPoint;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "latest_login_at")
    private LocalDateTime latestLoginAt;
}
