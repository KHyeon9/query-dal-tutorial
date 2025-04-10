package com.query_dsl.query_dsl_tutorial.base;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // QueryDSL을 사용하는데 필요한 주요 클래스로 JPAQuery의 인스턴스를 생성하는 역할
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
