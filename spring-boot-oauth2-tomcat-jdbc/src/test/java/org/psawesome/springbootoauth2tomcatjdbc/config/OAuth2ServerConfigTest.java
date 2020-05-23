package org.psawesome.springbootoauth2tomcatjdbc.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.config
 * author: PS
 * DATE: 2020-05-23 토요일 15:36
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OAuth2ServerConfigTest {

    @Autowired
    PasswordEncoder encoder;

    @Test
    void testInsertEncoder() {
        String testSecret = encoder.encode("testSecret");
        System.out.println("testSecret = " + testSecret);
    }
}