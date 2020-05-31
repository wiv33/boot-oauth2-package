package org.psawesome.springbootoauth2resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootOauth2ResourceServerApplication {

    public static void main(String[] args) {
//        System.setProperty("spring.profiles.active", "bearer");
//        System.setProperty("spring.profiles.active", "jwt");
        System.setProperty("spring.profiles.active", "publicKey");

        SpringApplication.run(SpringBootOauth2ResourceServerApplication.class, args);
    }

}
