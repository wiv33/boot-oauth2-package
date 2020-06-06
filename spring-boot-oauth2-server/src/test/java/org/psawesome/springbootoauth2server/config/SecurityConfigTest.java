package org.psawesome.springbootoauth2server.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * package: org.psawesome.springbootoauth2server.config
 * author: PS
 * DATE: 2020-06-06 토요일 22:46
 */
class SecurityConfigTest {


  @Test
  void testCreatePasswordEncoder() {
    PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    String password = delegatingPasswordEncoder.encode("password");
    System.out.println("password = " + password);
  }
}