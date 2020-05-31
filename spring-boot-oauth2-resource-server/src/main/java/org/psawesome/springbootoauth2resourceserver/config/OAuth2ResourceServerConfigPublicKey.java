package org.psawesome.springbootoauth2resourceserver.config;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;

/**
 * package: org.psawesome.springbootoauth2resourceserver.config
 * author: PS
 * DATE: 2020-05-31 일요일 22:47
 */
@Profile("publicKey")
@Configuration
public class OAuth2ResourceServerConfigPublicKey {


  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    Resource resource = new ClassPathResource("publicKey.txt");
    String publicKey = null;
    try {
      publicKey = IOUtils.toString(resource.getInputStream());
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
    converter.setVerifierKey(publicKey);
    return converter;
  }
}
