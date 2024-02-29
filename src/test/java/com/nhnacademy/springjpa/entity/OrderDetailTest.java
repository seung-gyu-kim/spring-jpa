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

//INSERT INTO order_details SET order_id = 1, product_id = 1, order_detail_quantity = 1, product_unit_cost = 3500000, product_name = '맥북 프로', product_short_desc = '소개', thumbnail_image_url = 'resources/thumbnails/1.jpg';

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class OrderDetailTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void findTest() {
        OrderDetail orderDetail = entityManager.find(OrderDetail.class, 1);

        assertSoftly(softly -> {
            softly.assertThat(orderDetail.getOrderDetailsId()).isEqualTo(1);
            softly.assertThat(orderDetail.getOrderId()).isEqualTo(1);
            softly.assertThat(orderDetail.getProductId()).isEqualTo(1);
            softly.assertThat(orderDetail.getOrderDetailQuantity()).isEqualTo(1);
            softly.assertThat(orderDetail.getProductUnitCost()).isEqualTo(BigDecimal.valueOf(3500000));
            softly.assertThat(orderDetail.getProductName()).isEqualTo("맥북 프로");
            softly.assertThat(orderDetail.getProductShortDesc()).isEqualTo("소개");
            softly.assertThat(orderDetail.getThumbnailImageUrl()).isEqualTo("resources/thumbnails/1.jpg");
        });
    }
}