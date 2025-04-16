package com.query_dsl.query_dsl_tutorial.boundedContext.user.repository;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// jpa 관련 코드만 사용
@Repository
public interface UserRepository extends JpaRepository<SiteUser, Long>, UserRepositoryCustom {

    List<SiteUser> findByInterestKeywords_keyword(String keyword);
}
