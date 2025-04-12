package com.query_dsl.query_dsl_tutorial.boundedContext.user.repository;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional // 각 테스트에 transactional을 붙이는 것과 같은 효과
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    // @Transactional + @Test 조합은 실패시 자동으로 rollback을 발생
    @Test
    @DisplayName("회원 생성")
    void cresteUser() {
        // {noop} : 비밀번호를 암호화하지 않고 사용
        SiteUser user3 = new SiteUser(
                null,
                "user3",
                "{noop}1234",
                "user3@email.com"
        );

        SiteUser user4 = new SiteUser(
                null,
                "user4",
                "{noop}1234",
                "user4@email.com"
        );

        userRepository.saveAll(Arrays.asList(user3, user4));
        assertThat(userRepository.findAll().size()).isEqualTo(4);
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

    @Test
    @DisplayName("모든 회원 수 조회")
    void allUserCount() {
        long count = userRepository.getQslCount();

        assertThat(count).isEqualTo(2);
    }
}