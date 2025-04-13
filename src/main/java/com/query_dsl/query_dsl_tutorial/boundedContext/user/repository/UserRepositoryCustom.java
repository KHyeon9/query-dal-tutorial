package com.query_dsl.query_dsl_tutorial.boundedContext.user.repository;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;

import java.util.List;

public interface UserRepositoryCustom {

    SiteUser getQslUser(Long id);

    long getQslCount();

    SiteUser getQslOldestUser();

    List<SiteUser> getQslOldAscUsers();

    List<SiteUser> searchQslUsers(String keyword);
}
