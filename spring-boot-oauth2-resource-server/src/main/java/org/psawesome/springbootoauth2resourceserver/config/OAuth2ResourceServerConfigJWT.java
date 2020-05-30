package org.psawesome.springbootoauth2resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * package: org.psawesome.springbootoauth2resourceserver.config
 * author: PS
 * DATE: 2020-05-31 일요일 07:16
 */
@Profile("jwt")
@Configuration
public class OAuth2ResourceServerConfigJWT {

  @Value("${security.oauth2.jwt.signKey}")
  private String signKey;

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    System.out.println("signKey = " + signKey);
    converter.setSigningKey(signKey);
    return converter;
  }
}
