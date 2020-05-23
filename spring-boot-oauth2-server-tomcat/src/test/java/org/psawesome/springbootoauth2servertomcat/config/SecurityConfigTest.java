package org.psawesome.springbootoauth2servertomcat.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * package: org.psawesome.springbootoauth2servertomcat.config
 * author: PS
 * DATE: 2020-05-23 토요일 12:43
 */
@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder encoder;
    private String password;

    @BeforeEach
    void setUp() {
        password = "secret";
    }

    @Test
    void testNotNullPasswordEncoder() {
        Assertions.assertNotNull(encoder);
    }

    @Test
    @DisplayName("{bcrypt} 여부 체크, 인코딩 전/후 비교")
    void testPasswordEncoderMatcher() {
        String encode = encoder.encode(password);
        assertTrue(encoder.matches(password, encode));
        assertTrue(encode.contains("{bcrypt}"));
        System.out.println("encode = " + encode);
    }

}