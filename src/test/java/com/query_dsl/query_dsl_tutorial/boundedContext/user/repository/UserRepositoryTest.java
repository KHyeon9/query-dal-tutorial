package com.query_dsl.query_dsl_tutorial.boundedContext.user.repository;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원 생성")
    void cresteUser() {
        // {noop} : 비밀번호를 암호화하지 않고 사용
        SiteUser user1 = new SiteUser(
                null,
                "user1",
                "{noop}1234",
                "user1@email.com"
        );

        SiteUser user2 = new SiteUser(
                null,
                "user2",
                "{noop}1234",
                "user2@email.com"
        );

        userRepository.saveAll(Arrays.asList(user1, user2));

        assert userRepository.count() == 2;
    }
}