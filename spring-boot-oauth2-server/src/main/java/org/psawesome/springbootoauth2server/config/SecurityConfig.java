package org.psawesome.springbootoauth2server.config;

import org.springframework.boot.actuate.autoconfigure.security.reactive.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
/*
    @Bean
    public PasswordEncoder passwordEncoder() {
//        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {


        // @formatter:off
        return http.csrf().disable()
                        .headers().frameOptions().disable()
                        .and()
                        .authorizeExchange()
                .pathMatchers("/private").hasRole("USER")
                .matchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
                .anyExchange().permitAll()
                .and().httpBasic()
                .and().build();
        // @formatter:on
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        return new MapReactiveUserDetailsService(
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("password"))
                        .roles("USER")
                        .build(),
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("password"))
                        .roles("USER,ADMIN")
                        .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .antMatchers("/oauth/**", "/oauth2/callback", "/h2-console/*")
                .permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic();
        // @formatter:on
    }
*/
}
