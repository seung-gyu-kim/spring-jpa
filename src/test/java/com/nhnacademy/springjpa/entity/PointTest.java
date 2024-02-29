package com.nhnacademy.springjpa.entity;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class PointTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testPointEntity() {
        Point point = entityManager.find(Point.class, 1);
        assertSoftly(softly -> {
            softly.assertThat(point.getPointId()).isEqualTo(1);
            softly.assertThat(point.getUserId()).isEqualTo("user");
            softly.assertThat(point.getPointValue()).isEqualTo(1000000);
            softly.assertThat(point.getPointType()).isEqualTo("POINT_JOIN");
            softly.assertThat(point.getPointDesc()).isEqualTo("가입 축하 포인트");
            softly.assertThat(point.getCreatedAt()).isEqualTo(LocalDateTime.of(2024, 2, 2, 0, 0, 0));
        });
    }
}