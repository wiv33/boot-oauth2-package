package org.psawesome.springbootoauth2server.repo;

import org.psawesome.springbootoauth2server.entity.MyUser;
import org.springframework.data.repository.Repository;

/**
 * package: org.psawesome.springbootoauth2server.repo
 * author: PS
 * DATE: 2020-05-20 수요일 01:19
 */
interface UserRepository extends Repository<Long, MyUser> {
}
