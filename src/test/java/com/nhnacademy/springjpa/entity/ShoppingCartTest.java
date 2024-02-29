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
import static org.junit.jupiter.api.Assertions.*;

// INSERT INTO shopping_carts SET product_id = 1, user_id = 'user', cart_quantity = 1;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ShoppingCartTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void findTest() {
        ShoppingCart shoppingCart = entityManager.find(ShoppingCart.class, new ShoppingCart.PK(1, "user"));

        assertSoftly(softly -> {
            softly.assertThat(shoppingCart.getProductId()).isEqualTo(1);
            softly.assertThat(shoppingCart.getUserId()).isEqualTo("user");
            softly.assertThat(shoppingCart.getCartQuantity()).isEqualTo(1);
        });
    }
}