package org.psawesome.springbootoauth2tomcatjdbc.repo;

import org.psawesome.springbootoauth2tomcatjdbc.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.repo
 * author: PS
 * DATE: 2020-05-23 토요일 17:30
 */
@Repository
public interface MyUserJpaRepo extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUid(String aEmail);
}
