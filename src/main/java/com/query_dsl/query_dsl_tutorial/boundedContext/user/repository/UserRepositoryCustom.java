package com.query_dsl.query_dsl_tutorial.boundedContext.user.repository;

import com.query_dsl.query_dsl_tutorial.boundedContext.user.entity.SiteUser;

public interface UserRepositoryCustom {

    SiteUser getQslUser(Long id);

    long getQslCount();

    SiteUser getQslOldestUser();
}
