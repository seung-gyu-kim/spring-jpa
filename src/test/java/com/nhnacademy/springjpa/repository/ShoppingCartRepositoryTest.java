package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.Product;
import com.nhnacademy.springjpa.entity.ShoppingCart;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class ShoppingCartRepositoryTest {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    User testUser;
    Product testProduct;

    @BeforeEach
    void setUp() {
        shoppingCartRepository.deleteAll();

        if(userRepository.findById("user").isEmpty()) {
            testUser = new User();
            testUser.setUserId("user");
            testUser.setUserName("사용자");
            testUser.setUserPassword("12345");
            testUser.setUserBirth("20240101");
            testUser.setUserAuth("ROLE_USER");
            userRepository.save(testUser);
        } else {
            testUser = userRepository.findById("user").get();
        }

        if(productRepository.findById(1).isEmpty()) {
            testProduct = Product.builder().productId(1).productName("testProduct1").productUnitCost(BigDecimal.valueOf(10000)).productQuantity(100).build();
            productRepository.save(testProduct);
        }
        if(productRepository.findById(2).isEmpty()) {
            testProduct = Product.builder().productId(2).productName("testProduct2").productUnitCost(BigDecimal.valueOf(10000)).productQuantity(100).build();
            productRepository.save(testProduct);
        }
        if(productRepository.findById(3).isEmpty()) {
            testProduct = Product.builder().productId(3).productName("testProduct3").productUnitCost(BigDecimal.valueOf(10000)).productQuantity(100).build();
            productRepository.save(testProduct);
        }
        if(productRepository.findById(4).isEmpty()) {
            testProduct = Product.builder().productId(4).productName("testProduct4").productUnitCost(BigDecimal.valueOf(10000)).productQuantity(100).build();
            productRepository.save(testProduct);
        }
    }

    @Test
    void allMethods() {
        List<ShoppingCart> list = new ArrayList<>();

        ShoppingCart test1 = new ShoppingCart();
        test1.setProductId(1);
        test1.setUserId("user");
        test1.setCartQuantity(1);
        test1.setProduct(Product.builder().productId(1).build());
        test1.setUser(testUser);
        ShoppingCart test2 = new ShoppingCart();
        test2.setProductId(2);
        test2.setUserId("user");
        test2.setCartQuantity(1);
        test2.setProduct(Product.builder().productId(2).build());
        test2.setUser(testUser);
        ShoppingCart test3 = new ShoppingCart();
        test3.setProductId(3);
        test3.setUserId("user");
        test3.setCartQuantity(1);
        test3.setProduct(Product.builder().productId(3).build());
        test3.setUser(testUser);
        ShoppingCart test4 = new ShoppingCart();
        test4.setProductId(4);
        test4.setUserId("user");
        test4.setCartQuantity(1);
        test4.setProduct(Product.builder().productId(4).build());
        test4.setUser(testUser);

        ShoppingCart save1 = shoppingCartRepository.save(test1);
        ShoppingCart save2 = shoppingCartRepository.save(test2);
        ShoppingCart save3 = shoppingCartRepository.save(test3);
        ShoppingCart save4 = shoppingCartRepository.save(test4);

        save4.setCartQuantity(10);
        ShoppingCart update = shoppingCartRepository.save(save4);

        list.add(save1);
        list.add(save2);
        list.add(save3);
        list.add(update);

        Assertions.assertAll(
                ()->Assertions.assertEquals(1, shoppingCartRepository.countByProductIdAndUserId(1, "user")),
                ()->Assertions.assertEquals(Optional.of(save1), shoppingCartRepository.findByProductIdAndUserId(1, "user")),
                ()->Assertions.assertEquals(Optional.of(update), shoppingCartRepository.findByProductIdAndUserId(4, "user")),
                ()->Assertions.assertEquals(list, shoppingCartRepository.findAllByUserId("user"))
        );

        shoppingCartRepository.deleteByProductIdAndUserId(4, "user");
        Assertions.assertEquals(3, shoppingCartRepository.findAllByUserId("user").size());

        shoppingCartRepository.deleteAllByUserId("user");
        Assertions.assertEquals(0, shoppingCartRepository.findAllByUserId("user").size());
    }
}