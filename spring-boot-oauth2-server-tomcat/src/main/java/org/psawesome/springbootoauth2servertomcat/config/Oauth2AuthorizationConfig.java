package org.psawesome.springbootoauth2servertomcat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {


    private final PasswordEncoder encoder;

    public Oauth2AuthorizationConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("testClientId")
                .secret(encoder.encode("testSecret"))
                .redirectUris("http://localhost:8090/oauth2/token")
                /*
                "authorization_code", "password", "client_credentials", "implicit", "refresh_token"
                */
                .authorizedGrantTypes("authorization_code")
                .scopes("read", "write")
                .accessTokenValiditySeconds(30000)
        ;
    }
}
