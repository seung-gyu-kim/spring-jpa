package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCart.PK> {
    Optional<ShoppingCart> findByProductIdAndUserId(Integer productId, String userId);
    List<ShoppingCart> findAllByUserId(String userId);
    void deleteByProductIdAndUserId(Integer productId, String userId);
    void deleteAllByUserId(String userId);
    int countByProductIdAndUserId(Integer productId, String userId);
}
