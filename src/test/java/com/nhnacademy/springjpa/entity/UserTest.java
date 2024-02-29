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
class UserTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testUserEntity() {
        User user = entityManager.find(User.class, "admin");
        assertSoftly(softly -> {
            softly.assertThat(user.getUserId()).isEqualTo("admin");
            softly.assertThat(user.getUserName()).isEqualTo("관리자");
            softly.assertThat(user.getUserPassword()).isEqualTo("12345");
            softly.assertThat(user.getUserBirth()).isEqualTo("19960826");
            softly.assertThat(user.getUserAuth()).isEqualTo("ROLE_ADMIN");
            softly.assertThat(user.getUserPoint()).isEqualTo(100000000);
            softly.assertThat(user.getCreatedAt()).isEqualTo(LocalDateTime.of(2024, 2, 2, 0, 0, 0));
            softly.assertThat(user.getLatestLoginAt()).isNull();
        });

    }
}