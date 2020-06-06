package org.psawesome.springbootoauth2server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    // @formatter:off
    http
      .authorizeExchange()
        .anyExchange().authenticated()
          .pathMatchers("/login", "/webjars/**", "/users/signup").permitAll()
          .and()
      .httpBasic().and()
      .formLogin()
        .loginPage("/login");
    return http.build();
    // @formatter:on
  }
/*

  // @formatter:off
  @Bean
  MapReactiveUserDetailsService userDetailsService() {
    UserDetails user = User.withUsername("ps@example.com")
            .password("{bcrypt}$2a$10$7VIbyaF.PV.LNNhLAbV.VOjsXtOZmwpvDGdrBlZ.4X9LutZlFqPXm")
            .roles("USER")
            .build();
    return new MapReactiveUserDetailsService(user);
  }
  // @formatter:on
*/

}
