package org.psawesome.springbootoauth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class SpringBootOauth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauth2ServerApplication.class, args);
    }

}
