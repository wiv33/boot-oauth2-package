package org.psawesome.springbootoauth2tomcatjdbc.config.activeResource;

import lombok.RequiredArgsConstructor;
import org.psawesome.springbootoauth2tomcatjdbc.service.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.config.activeResource
 * author: PS
 * DATE: 2020-05-31 일요일 06:54
 */
@Profile("resource-jwt")
@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class OAuth2ConfigServerResourceJWT extends AuthorizationServerConfigurerAdapter {

  @Value("${security.oauth2.jwt.signKey}")
  private String signKey;

  private final CustomUserDetailService userDetailService;
  private final PasswordEncoder passwordEncoder;
  private final DataSource dataSource;

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

  @Bean
  public AccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(signKey);
    return converter;
  }

  /* 토큰 검증을 보낼 때 Authorization /oauth/check_token을 호출하기 때문에 token Access를 허용한다.*/
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    security.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()") //allow check token
            .allowFormAuthenticationForClients();
  }
}
