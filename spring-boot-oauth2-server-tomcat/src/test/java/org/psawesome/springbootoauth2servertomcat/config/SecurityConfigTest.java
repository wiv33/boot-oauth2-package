package org.psawesome.springbootoauth2servertomcat.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
        System.out.println("secret = " + encode);
//        {bcrypt}$2a$10$PtO1jJJQoAEn6HNrwybjeuXkcAJClwTneQ0XojIxPyBKZeshHJhqe
//        {bcrypt}$2a$10$nCoG6NV7.AA5b9z1nApcG.CEsClHdj7nNQhNjz3SJz0FN8VvoLSFy
    }

    @Test
    @DisplayName("Encoder 변경")
    void testChangePasswordEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        String encode = encoder.encode(password);
        assertTrue(encoder.matches(password, encode));
        assertTrue(encode.contains("{pbkdf2}"));
        System.out.println("pbkdf2 = " + encode);
//        {pbkdf2}e2aa21d35c4dcd024476b582a3838faa76779f606a08ad477acb775d22b9886295cc9fe0ad2ae703
    }

    @Test
    @DisplayName("Encoder SHA-256으로 변경")
    void testChangePasswordEncoderBySHA256() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
        encoder = new DelegatingPasswordEncoder("SHA-256", encoders);

        String encode = encoder.encode(password);
        assertTrue(encoder.matches(password, encode));
        System.out.println("SHA-256 = " + encode);
//        {SHA-256}{NSbA6ueE7VcroNR3Gilf/w3XX1qSiJQ91fIWqzxYmMY=}562e8d1fd8cde7dca69898c484855933d024b41055c12ee8b135a4ea6b72b453

    }

    @Test
    @DisplayName("Custom 시 인코더 값이 없을 경우 발생하는 에러 테스트")
    void testNotExistsBcryptEncoder() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
//        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));

        assertThrows(IllegalArgumentException.class,
                () -> encoder = new DelegatingPasswordEncoder("bcrypt", encoders));
    }
}