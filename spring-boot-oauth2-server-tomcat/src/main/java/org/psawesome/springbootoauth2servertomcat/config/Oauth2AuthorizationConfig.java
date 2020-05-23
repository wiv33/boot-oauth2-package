package org.psawesome.springbootoauth2servertomcat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {


    private final PasswordEncoder encoder;
    private final DataSource dataSource;

    public Oauth2AuthorizationConfig(PasswordEncoder encoder, DataSource dataSource) {
        this.encoder = encoder;
        this.dataSource = dataSource;
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
                .authorizedGrantTypes("authorization_code,refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(30000)
        ;
//        clients.jdbc(dataSource).passwordEncoder(encoder);
    }
}
