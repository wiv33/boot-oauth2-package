package org.psawesome.springbootoauth2servertomcat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * package: org.psawesome.springbootoauth2servertomcat
 * author: PS
 * DATE: 2020-05-23 토요일 12:39
 */
@SpringBootTest
public class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder encoder;

    @Test
    void testNotNullPasswordEncoder() {
        Assertions.assertNotNull(encoder);
    }
}
