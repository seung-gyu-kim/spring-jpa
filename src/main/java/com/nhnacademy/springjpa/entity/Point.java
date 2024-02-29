package com.nhnacademy.springjpa.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
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
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "points")
public class Point {
    @Id
    @Column(name = "point_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pointId;
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private String userId;
    @Column(name = "point_value", nullable = false)
    private Integer pointValue;
    @Column(name = "point_type", nullable = false)
    private String pointType;
    @Column(name = "point_desc")
    private String pointDesc;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
