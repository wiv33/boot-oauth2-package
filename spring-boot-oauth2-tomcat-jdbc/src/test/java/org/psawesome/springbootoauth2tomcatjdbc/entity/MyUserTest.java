package org.psawesome.springbootoauth2tomcatjdbc.entity;

import org.junit.jupiter.api.Test;
import org.psawesome.springbootoauth2tomcatjdbc.repo.MyUserJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.entity
 * author: PS
 * DATE: 2020-05-23 토요일 17:50
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MyUserTest {

    @Autowired
    MyUserJpaRepo repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void testInsertUser() {
        repo.save(MyUser.builder()
                .uid("psk@gmail.com")
                .password(passwordEncoder.encode("1234"))
                .name("body")
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
    }
}