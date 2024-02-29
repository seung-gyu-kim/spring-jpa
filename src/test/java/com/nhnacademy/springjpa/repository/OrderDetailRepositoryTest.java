package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// N + 1 문제 발생
@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    void findById() {
        System.out.println("------------------------------------------------------------");
//        orderDetailRepository.findById(1);
        System.out.println(orderDetailRepository.findById(1));
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void findAllByOrderId() {
        System.out.println("------------------------------------------------------------");
//        orderDetailRepository.findAllByOrderId(1);
        System.out.println(orderDetailRepository.findAllByOrderId(1));
        System.out.println("------------------------------------------------------------");
    }

    @Test
    void findAllById() {
        System.out.println("------------------------------------------------------------");
//        orderDetailRepository.findAllById(List.of(1));
        System.out.println(orderDetailRepository.findAllById(List.of(1)));
        System.out.println("------------------------------------------------------------");
    }

    @Test
    @DisplayName("N + 1 해결 : JPQL join fetch")
    void findAllByOrderIdWithJPQLJoinFetch() {
        System.out.println("------------------------------------------------------------");
        System.out.println(orderDetailRepository.findAllByOrderIdWithJPQLJoinFetch(1));
        System.out.println("------------------------------------------------------------");
    }

    @Test
    @DisplayName("N + 1 해결 : EntityGraph")
    void getAllByOrderId() {
        System.out.println("------------------------------------------------------------");
        System.out.println(orderDetailRepository.getAllByOrderId(1));
        System.out.println("------------------------------------------------------------");
    }

    @Test
    @DisplayName("N + 1 해결 : Querydsl fetch join")
    void findAllByOrderIdWithQueryDslFetchJoin() {
        System.out.println("------------------------------------------------------------");
        System.out.println(orderDetailRepository.findAllByOrderIdWithQuerydslFetchJoin(1));
        System.out.println("------------------------------------------------------------");
    }
}