package org.psawesome.springbootoauth2clienttomcat.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * package: org.psawesome.springbootoauth2clienttomcat.controller
 * author: PS
 * DATE: 2020-05-22 금요일 21:26
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OAuthTokenControllerTest {

    @Autowired
    ApplicationContext application;

    @Autowired
    OAuthTokenController controller;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void initController() {
        Assertions.assertNotNull(application);
        Assertions.assertNotNull(controller);
    }


    @Test
    void testGetToken() {
        URI uri = URI.create("http://localhost:8081/oauth/authorize?client_id=testClientId&redirect_uri=https://localhost:8090/oauth2/token&response_code=code&scope=read");
        ResponseEntity<String> response = testRestTemplate.getForEntity(uri, String.class);
        System.out.println("response = " + response);
    }
}