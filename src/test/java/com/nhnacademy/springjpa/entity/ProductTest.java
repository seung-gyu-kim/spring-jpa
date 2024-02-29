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

import java.math.BigDecimal;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.junit.jupiter.api.Assertions.*;

//INSERT INTO products SET product_name = '맥북 프로', product_unit_cost = 3500000, product_short_desc = '소개', product_desc = '설명', product_quantity = 100, detail_image_url = 'resources/details/1.jpg', thumbnail_image_url = 'resources/thumbnails/1.jpg';

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ProductTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void findTest() {
        Product product = entityManager.find(Product.class, 1);

        assertSoftly(softly -> {
            softly.assertThat(product.getProductId()).isEqualTo(1);
            softly.assertThat(product.getProductName()).isEqualTo("맥북 프로");
            softly.assertThat(product.getProductUnitCost()).isEqualTo(BigDecimal.valueOf(3500000));
            softly.assertThat(product.getProductShortDesc()).isEqualTo("소개");
            softly.assertThat(product.getProductDesc()).isEqualTo("설명");
            softly.assertThat(product.getProductQuantity()).isEqualTo(100);
            softly.assertThat(product.getDetailImageUrl()).isEqualTo("resources/details/1.jpg");
            softly.assertThat(product.getThumbnailImageUrl()).isEqualTo("resources/thumbnails/1.jpg");
        });
    }
}