package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.ProductDto;
import com.nhnacademy.springjpa.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    void deleteByProductId(Integer productId);

    List<Product> findAllByProductNameContaining(String search);

    int countByProductId(long productId);

    Page<ProductDto> findAllBy(Pageable pageable);

    Page<ProductDto> findAllByProductNameContaining(String search, Pageable pageable);

}
