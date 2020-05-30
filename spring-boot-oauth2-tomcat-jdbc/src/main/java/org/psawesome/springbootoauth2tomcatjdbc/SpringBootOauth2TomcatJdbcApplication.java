package org.psawesome.springbootoauth2tomcatjdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootOauth2TomcatJdbcApplication {

    public static void main(String[] args) {
//        System.setProperty("spring.profiles.active", "jwt");
//        System.setProperty("spring.profiles.active", "resource");
        System.setProperty("spring.profiles.active", "resource-jwt");
        SpringApplication.run(SpringBootOauth2TomcatJdbcApplication.class, args);
    }
}