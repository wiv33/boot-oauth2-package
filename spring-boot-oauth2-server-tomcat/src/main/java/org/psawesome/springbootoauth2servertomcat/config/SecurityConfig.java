package org.psawesome.springbootoauth2servertomcat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder noOpPasswordEncoder() {
//        HashMap<String, PasswordEncoder> stringPasswordEncoderHashMap = new HashMap<>();
//        stringPasswordEncoderHashMap.put("pass", PasswordEncoderFactories.createDelegatingPasswordEncoder());
//        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("userId", stringPasswordEncoderHashMap);
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("pass")
                .roles("USER");
    }
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/oauth/**", "/oauth2/callback").permitAll()
                .and()
                .formLogin().and()
                .httpBasic();
    }
}
