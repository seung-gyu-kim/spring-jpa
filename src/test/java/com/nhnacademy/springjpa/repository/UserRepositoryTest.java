package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.config.RootConfig;
import com.nhnacademy.springjpa.config.WebConfig;
import com.nhnacademy.springjpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User("nhnacademy-test-user","nhn아카데미","nhnacademy-test-password","19900505", "ROLE_USER", 100_0000, LocalDateTime.now(), null);
        userRepository.save(testUser);
    }

    @Test
    @Order(1)
    @DisplayName("로그인: user 조회 by userId and userPassword")
    void findByUserIdAndUserPassword() {
        Optional<User> userOptional = userRepository.findByUserIdAndUserPassword(testUser.getUserId(),testUser.getUserPassword());
        Assertions.assertEquals(testUser,userOptional.get());
    }

    @Test
    @Order(2)
    @DisplayName("로그인 : sql injection 방어")
//    @Disabled
    void findByUserIdAndUserPassword_sql_injection(){
        //테스트 코드가 통과할 수 있도록  userRepository.findByUserIdAndUserPassword를 수정하세요.
        String password="' or '1'='1";
        Optional<User> userOptional = userRepository.findByUserIdAndUserPassword(testUser.getUserId(),password);
        log.debug("user:{}",userOptional.orElse(null));
        Assertions.assertFalse(userOptional.isPresent());
    }

    @Test
    @Order(3)
    @DisplayName("user 조회 by uerId")
    void findById() {
        Optional<User> userOptional = userRepository.findById(testUser.getUserId());
        Assertions.assertEquals(testUser,userOptional.get());
    }

    @Test
    @Order(4)
    @DisplayName("user 등록")
    void save() {
        User newUser = new User("nhnacademy-test-user2","nhn아카데미2","nhnacademy-test-password2","19900502", "ROLE_USER",100_0000,LocalDateTime.now(),null);
        User result = userRepository.save(newUser);
        Assertions.assertAll(
                ()->Assertions.assertNotNull(result),
                ()->Assertions.assertEquals(newUser, userRepository.findById(newUser.getUserId()).get())
        );
    }

    @Disabled
    @Test
    @Order(5)
    @DisplayName("user 중복 등록 - 제약조건 확인")
    void save_duplicate_user_id() {
        Throwable throwable = Assertions.assertThrows(RuntimeException.class,()->{
            userRepository.save(testUser);
        });
        Assertions.assertTrue(throwable.getMessage().contains(SQLIntegrityConstraintViolationException.class.getName()));
        log.debug("errorMessage:{}", throwable.getMessage());
    }

    @Test
    @Order(6)
    @DisplayName("user 삭제")
    void deleteByUserId() {
        userRepository.delete(testUser);
        Assertions.assertAll(
                ()->Assertions.assertFalse(userRepository.findById(testUser.getUserId()).isPresent())
        );
    }

    @Test
    @Order(7)
    @DisplayName("user 수정")
    void update() {
        testUser.setUserName("nhn아카데미");
        testUser.setUserAuth("ROLE_ADMIN");
        testUser.setUserBirth("20100505");
        testUser.setUserPoint(20_0000);
        testUser.setUserPassword("new-password");

        User result = userRepository.save(testUser);
        Assertions.assertAll(
                ()-> Assertions.assertNotNull(result),
                ()-> Assertions.assertEquals(testUser, userRepository.findById(testUser.getUserId()).get())
        );
    }

    @Test
    @Order(8)
    @DisplayName("최근 로그인시간 update")
    void updateLatestLoginAtByUserId() {
        testUser.setLatestLoginAt(LocalDateTime.now());
        User result = userRepository.save(testUser);
        Assertions.assertNotNull(result);
    }
}