package org.psawesome.springbootoauth2resourceserver.repo;

import org.psawesome.springbootoauth2resourceserver.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<MyUser, Long> {

}
