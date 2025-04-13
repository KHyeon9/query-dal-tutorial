package com.query_dsl.query_dsl_tutorial.boundedContext.user.repository;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.QSiteUser;
import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

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

    @Override
    public long getQslCount() {
        QSiteUser qslUser = QSiteUser.siteUser;

        return queryFactory
                .select(qslUser.count())
                .from(qslUser)
                .fetchOne();
    }

    @Override
    public SiteUser getQslOldestUser() {
        QSiteUser qslUser = QSiteUser.siteUser;

        return queryFactory
                .selectFrom(qslUser)
                .orderBy(qslUser.id.asc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public List<SiteUser> getQslOldAscUsers() {
        QSiteUser qslUser = QSiteUser.siteUser;

        return queryFactory
                .selectFrom(qslUser)
                .orderBy(qslUser.id.asc())
                .fetch();
    }

    @Override
    public List<SiteUser> searchQslUsers(String keyword) {
        QSiteUser qslUser = QSiteUser.siteUser;

        return queryFactory
                .selectFrom(qslUser)
                .where(
                        qslUser.username.contains(keyword)
                                .or(qslUser.email.contains(keyword))
                )
                .fetch();
    }

    @Override
    public Page<SiteUser> searchQslUsers(String keyword, Pageable pageable) {
        QSiteUser qslUser = QSiteUser.siteUser;

        // 검색 조건
        // BooleanExpression: 검색 조건을 처리하는 객체
        // containsIgnoreCase: 대소문자 구분 X
        BooleanExpression predicate = qslUser
                .username.containsIgnoreCase(keyword)
                .or(qslUser.email.containsIgnoreCase(keyword));
        
        // 페이징 조회
        // QueryResults: 쿼리 실행 결과와 함께 페이징을 위한 추가 정보 포함
        QueryResults<SiteUser> queryResults = queryFactory
                .selectFrom(qslUser)
                .where(predicate)
                .orderBy(qslUser.id.asc())
                .offset(pageable.getOffset()) // 시작 위치 (limit {여기} {?} 시작 위치)
                .limit(pageable.getPageSize()) // 페이지 크기 (limit {?} {여기} 끝나는 위치)
                .fetchResults(); // 데이터와 총 데이터 수를 가져옴

        // 조회 결과를 리스트로
        List<SiteUser> users = queryResults.getResults();

        // 총 페이지 조회
        long total = queryResults.getTotal();

        // PageImpl: 페이징된 데이터와 메타 데이터(전체 갯수, 페이지 정보)를 포함
        return new PageImpl<>(users, pageable, total);
    }
}
