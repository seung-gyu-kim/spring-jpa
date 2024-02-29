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

//INSERT INTO addresses SET user_id = 'user', address = '서울 송파구 올림픽로 300', is_default = 1;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class AddressTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testAddressEntity() {
        Address address = entityManager.find(Address.class, new Address.PK("user", "서울 송파구 올림픽로 300"));

        assertSoftly(softly -> {
            softly.assertThat(address.getUserId()).isEqualTo("user");
            softly.assertThat(address.getAddress()).isEqualTo("서울 송파구 올림픽로 300");
            softly.assertThat(address.isDefault()).isEqualTo(true);
        });
    }
}