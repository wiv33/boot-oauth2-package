package org.psawesome.springbootoauth2tomcatjdbc.config.activeResource;

import lombok.RequiredArgsConstructor;
import org.psawesome.springbootoauth2tomcatjdbc.service.security.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.config.activeResource
 * author: PS
 * DATE: 2020-05-31 일요일 22:53
 */
@Profile("resource-publicKey")
@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class OAuth2ConfigServerResourcePublicKey extends AuthorizationServerConfigurerAdapter {

  private final CustomUserDetailService userDetailService;
  private final PasswordEncoder passwordEncoder;
  private final DataSource dataSource;

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    security.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()") //allow check token
            .allowFormAuthenticationForClients();
  }


  /**
   * 클라이언트 정보 주입 방식을 jdbcdetail로 변경
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    super.configure(endpoints);
    endpoints.accessTokenConverter(jwtAccessTokenConverter()).userDetailsService(userDetailService);
  }

  /**
   * jwt converter - 비대칭 키 sign
   */
  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new FileSystemResource("src/main/resources/oauth2jwt.jks"), "oauth2jwtpass".toCharArray());
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setKeyPair(keyStoreKeyFactory.getKeyPair("oauth2jwt"));
    return converter;
  }

}
