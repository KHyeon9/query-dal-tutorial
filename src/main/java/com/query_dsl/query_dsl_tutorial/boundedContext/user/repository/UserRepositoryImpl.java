package com.query_dsl.query_dsl_tutorial.boundedContext.user.repository;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.QSiteUser;
import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

// QueryDSL의 구현체
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    // QueryDSL에 관련된 쿼리 코드를 작성(구현)
    @Override
    public SiteUser getQslUser(Long id) {
        /*
        * select *
        * from site_user
        * where id = 1;
        * */

        QSiteUser siteUser = QSiteUser.siteUser;
        // queryFactory
        //   .select(siteUser) select *
        //   .from(siteUser); from site_user

        return queryFactory
                .selectFrom(siteUser) // select * from site_user
                .where(siteUser.id.eq(id)) // where id = 1
                .fetchOne(); // 단일 결과 반환
    }
}
