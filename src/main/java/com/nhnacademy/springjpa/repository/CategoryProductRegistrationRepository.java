package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.CategoryProductRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryProductRegistrationRepository extends JpaRepository<CategoryProductRegistration, CategoryProductRegistration.PK> {
    long countByProductIdAndCategoryId(Integer productId, Integer categoryId);

    long countByCategoryId(Integer categoryId);

    void deleteAllByProductId(Integer productId);
}
