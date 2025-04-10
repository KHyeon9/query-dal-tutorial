package com.query_dsl.query_dsl_tutorial.boundedContext.user.repository;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


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
        assertThat(userRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("1번 회원을 Qsl로 가져오기")
    void getUser1Qsl() {
        SiteUser getUser1 = userRepository.getQslUser(1L);

        assertThat(getUser1.getId()).isEqualTo(1L);
        assertThat(getUser1.getUsername()).isEqualTo("user1");
        assertThat(getUser1.getPassword()).isEqualTo("{noop}1234");
        assertThat(getUser1.getEmail()).isEqualTo("user1@email.com");
    }

    @Test
    @DisplayName("2번 회원을 Qsl로 가져오기")
    void getUser2Qsl() {
        SiteUser getUser2 = userRepository.getQslUser(2L);

        assertThat(getUser2.getId()).isEqualTo(2L);
        assertThat(getUser2.getUsername()).isEqualTo("user2");
        assertThat(getUser2.getPassword()).isEqualTo("{noop}1234");
        assertThat(getUser2.getEmail()).isEqualTo("user2@email.com");
    }
}