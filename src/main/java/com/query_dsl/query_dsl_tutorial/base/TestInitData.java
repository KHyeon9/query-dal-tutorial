package com.query_dsl.query_dsl_tutorial.base;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;
import com.query_dsl.query_dsl_tutorial.boundedContext.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test") // 이 클래스 안에 정의된 Bean들은 test 모드에서만 실행
public class TestInitData {

    // 앱 실행 직후 초기 데이터 셋팅 및 초기화
    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return args -> {
            // 이 부분은 Spring boot 앱이 실행되고, 본격적으로 작동하기 전 실행
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

            List<SiteUser> users = userRepository.saveAll(Arrays.asList(user1, user2));
        };
    }
}
