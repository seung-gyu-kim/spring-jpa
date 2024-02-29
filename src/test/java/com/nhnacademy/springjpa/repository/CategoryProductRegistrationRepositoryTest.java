package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.CategoryProductRegistration;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class CategoryProductRegistrationRepositoryTest {

    @Autowired
    private CategoryProductRegistrationRepository categoryProductRegistrationRepository;

    @Test
    void test() {
        CategoryProductRegistration categoryProductRegistration = new CategoryProductRegistration();
        categoryProductRegistration.setProductId(2);
        categoryProductRegistration.setCategoryId(1);
        categoryProductRegistrationRepository.save(categoryProductRegistration);
        categoryProductRegistration.setCategoryId(2);
        categoryProductRegistrationRepository.save(categoryProductRegistration);

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(categoryProductRegistrationRepository.countByProductIdAndCategoryId(2, 2)).isEqualTo(1);
        softly.assertThat(categoryProductRegistrationRepository.countByCategoryId(2)).isEqualTo(2);

        categoryProductRegistrationRepository.deleteAllByProductId(1);

        softly.assertThat(categoryProductRegistrationRepository.countByProductIdAndCategoryId(1, 1)).isEqualTo(0);
        softly.assertThat(categoryProductRegistrationRepository.countByProductIdAndCategoryId(1, 2)).isEqualTo(0);
        softly.assertThat(categoryProductRegistrationRepository.countByCategoryId(2)).isEqualTo(1);

        softly.assertAll();
    }
}