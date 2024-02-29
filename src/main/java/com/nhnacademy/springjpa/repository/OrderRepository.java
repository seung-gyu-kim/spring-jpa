package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserId(String userId);
}
