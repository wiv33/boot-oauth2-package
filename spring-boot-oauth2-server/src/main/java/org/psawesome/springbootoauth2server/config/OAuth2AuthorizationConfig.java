package org.psawesome.springbootoauth2server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //더미
                .withClient("testClientId")
                .secret("testSecret")

                // 인증 완료 후 code 보낼 주소
                .redirectUris("http://localhost:8081/oauth2/callback")

                // Authorization_code, implicit, password credential, client credential
                .authorizedGrantTypes("authorization_code")

                // access token 인증 후 접근할 수 있는 리소스 범위 설정
                .scopes("read", "write")

                .accessTokenValiditySeconds(30000);
    }
}
