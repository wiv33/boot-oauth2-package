package org.psawesome.springbootoauth2tomcatjdbc.config.activeResource;

import lombok.RequiredArgsConstructor;
import org.psawesome.springbootoauth2tomcatjdbc.service.security.CustomUserDetailService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Profile("resource")
@EnableAuthorizationServer
@Configuration
@RequiredArgsConstructor
public class OAuth2ConfigServerResource extends AuthorizationServerConfigurerAdapter {

  private final DataSource dataSource;
  private final CustomUserDetailService userDetailService;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
    endpoints.tokenStore(new JdbcTokenStore(dataSource)).userDetailsService(userDetailService);
  }
}
