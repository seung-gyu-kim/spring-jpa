package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.Address;
import com.nhnacademy.springjpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    private User testUser;

    //INSERT INTO users SET user_id = 'admin', user_name = '관리자', user_password = '12345', user_birth = '19960826', user_auth = 'ROLE_ADMIN', user_point = 100000000, created_at = '2024-02-02 00:00:00';
    //INSERT INTO users SET user_id = 'user', user_name = '회원', user_password = '12345', user_birth = '19960826', user_auth = 'ROLE_USER', user_point = 100000000, created_at = '2024-02-02 00:00:00';

    @BeforeEach
    void setUp() {
//        testUser = new User("user", "회원", "12345", "19960826", "ROLE_USER", 100000000, LocalDateTime.of(2024, 2, 2, 0, 0, 0), null);
        testUser = new User();
        testUser.setUserId("user");
    }

    @Test
    void allMethods() {
        Address testAddress = new Address();
        testAddress.setUserId(testUser.getUserId());
        testAddress.setAddress("address_data_for_test");
        testAddress.setUser(testUser);

        Address save = addressRepository.save(testAddress);
        System.out.println(testAddress);
        System.out.println(save);

        Assertions.assertEquals(1, addressRepository.countByUserIdAndAddress(testAddress.getUserId(), testAddress.getAddress()));
        Assertions.assertEquals(save, addressRepository.findAllByUserId(testUser.getUserId()).get(1));

        addressRepository.delete(testAddress);
        Assertions.assertEquals(1, addressRepository.findAllByUserId(testUser.getUserId()).size());
    }
}