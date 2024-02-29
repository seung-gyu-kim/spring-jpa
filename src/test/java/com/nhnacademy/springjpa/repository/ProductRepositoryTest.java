package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setProductName("testProduct");
        testProduct.setProductUnitCost(BigDecimal.valueOf(1000));
        testProduct.setProductShortDesc("한 줄 설명");
        testProduct.setProductDesc("자세한 설명");
        testProduct.setProductQuantity(100);
        productRepository.save(testProduct);
    }

    @Test
    @DisplayName("product 조회 by productId")
    void findById() {
        Optional<Product> productOptional = productRepository.findById(testProduct.getProductId());
        Assertions.assertEquals(testProduct, productOptional.get());
    }

    @Test
    @DisplayName("product 등록")
    void save() {
        Product newProduct = new Product();
        newProduct.setProductName("NEW Product");
        newProduct.setProductUnitCost(BigDecimal.valueOf(1000));
        newProduct.setProductShortDesc("한 줄 설명");
        newProduct.setProductDesc("자세한 설명");
        newProduct.setProductQuantity(100);
        Product result = productRepository.save(newProduct);
        Assertions.assertAll(
                ()->Assertions.assertNotNull(result),
                ()->Assertions.assertEquals(newProduct, productRepository.findById(newProduct.getProductId()).get())
        );
    }

    @Test
    @DisplayName("product 삭제")
    void deleteByUserId() {
        productRepository.deleteByProductId(testProduct.getProductId());
        Assertions.assertAll(
                ()->Assertions.assertFalse(productRepository.findById(testProduct.getProductId()).isPresent())
        );
    }

    @Test
    @DisplayName("product 수정")
    void update() {
        testProduct.setProductName("updatedProduct");
        testProduct.setProductUnitCost(BigDecimal.valueOf(2000));
        testProduct.setProductShortDesc("한 줄 설명 추가");
        testProduct.setProductDesc("자세한 설명 추가");
        testProduct.setProductQuantity(200);

        Product result = productRepository.save(testProduct);
        Assertions.assertAll(
                ()-> Assertions.assertNotNull(result),
                ()-> Assertions.assertEquals(testProduct, productRepository.findById(testProduct.getProductId()).get())
        );
    }
}