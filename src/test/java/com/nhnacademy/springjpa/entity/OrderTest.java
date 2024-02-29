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
import static org.junit.jupiter.api.Assertions.*;

//INSERT INTO orders SET user_id = 'user', order_date = '2024-02-02 00:00:00', order_ship_date = '2024-02-03 07:00:00', order_address = '서울 송파구 올림픽로 300';

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class OrderTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testOrderEntity() {
        Order order = entityManager.find(Order.class, 1);
        assertSoftly(softly -> {
            softly.assertThat(order.getOrderId()).isEqualTo(1);
            softly.assertThat(order.getUserId()).isEqualTo("user");
            softly.assertThat(order.getOrderDate()).isEqualTo(LocalDateTime.of(2024, 2, 2, 0, 0, 0));
            softly.assertThat(order.getOrderShipDate()).isEqualTo(LocalDateTime.of(2024, 2, 3, 7, 0, 0));
            softly.assertThat(order.getOrderAddress()).isEqualTo("서울 송파구 올림픽로 300");
        });
    }
}