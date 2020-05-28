package org.psawesome.springbootoauth2tomcatjdbc.config.activeResource;

import lombok.RequiredArgsConstructor;
import org.psawesome.springbootoauth2tomcatjdbc.service.security.CustomUserDetailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Profile("resource")
@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class OAuth2ConfigServerResource extends AuthorizationServerConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final DataSource dataSource;
  private final CustomUserDetailService userDetailService;

  //  DB 관리
/*
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.tokenStore(new JdbcTokenStore(dataSource)).userDetailsService(userDetailService);
  }
*/
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
  }

  /* 토큰 검증을 보낼 때 Authorization /oauth/check_token을 호출하기 때문에 token Access를 허용한다.*/
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) {
    security.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()") //allow check token
            .allowFormAuthenticationForClients();
  }
}
