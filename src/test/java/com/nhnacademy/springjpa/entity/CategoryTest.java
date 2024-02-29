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

import static org.assertj.core.api.SoftAssertions.assertSoftly;

// INSERT INTO categories SET category_name = '노트북';
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class CategoryTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void findTest() {
        Category category = entityManager.find(Category.class, 1);

        assertSoftly(softly -> {
            softly.assertThat(category.getCategoryId()).isEqualTo(1);
            softly.assertThat(category.getCategoryName()).isEqualTo("전자제품");
        });
    }
}