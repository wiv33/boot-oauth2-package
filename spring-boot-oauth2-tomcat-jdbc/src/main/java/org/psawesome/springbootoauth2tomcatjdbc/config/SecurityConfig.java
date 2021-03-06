package org.psawesome.springbootoauth2tomcatjdbc.config;

import lombok.RequiredArgsConstructor;
import org.psawesome.springbootoauth2tomcatjdbc.common.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.config
 * author: PS
 * DATE: 2020-05-23 토요일 15:39
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  final CustomAuthenticationProvider provider;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(provider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .cors().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests().antMatchers("/oauth/**", "/oauth2/**").permitAll()
            .and()
            .formLogin()
            .and()
            .httpBasic()
    ;
  }
}
