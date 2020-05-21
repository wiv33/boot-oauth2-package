package org.psawesome.springbootoauth2server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;

    public OAuth2AuthorizationConfig(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //더미
                .withClient("testClientId")
                .secret(passwordEncoder.encode("testSecret"))

                // 인증 완료 후 code 보낼 주소
                .redirectUris("http://localhost:8081/oauth2/callback")

                // Authorization_code, implicit, password credential, client credential
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")

                // access token 인증 후 접근할 수 있는 리소스 범위 설정
                .scopes("read_profile_info", "write")

                .refreshTokenValiditySeconds(240000)
                .accessTokenValiditySeconds(30000);
    }
}
