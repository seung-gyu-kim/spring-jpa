package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void test() {
        Category category1 = new Category();
        Category category2 = new Category();
        Category category3 = new Category();
//        category.setCategoryId(2);
        category1.setCategoryName("임시");
        category2.setCategoryName("임시");
        category3.setCategoryName("임시");
        Category save = categoryRepository.save(category1);
        Category save1 = categoryRepository.save(category2);
        Category save2 = categoryRepository.save(category3);
        log.info("save = {}", save);
        log.info("save = {}", save1);
        log.info("save = {}", save2);


        long result = categoryRepository.countByCategoryId(1);
        assertThat(result).isEqualTo(1L);
    }
}