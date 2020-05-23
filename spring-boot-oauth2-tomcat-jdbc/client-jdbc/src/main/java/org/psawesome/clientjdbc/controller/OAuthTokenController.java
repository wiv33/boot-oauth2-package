package org.psawesome.clientjdbc.controller;

import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.psawesome.clientjdbc.common.OAuthToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * package: org.psawesome.clientjdbc.controller
 * author: PS
 * DATE: 2020-05-23 토요일 15:52
 */
@RestController
@RequestMapping("/oauth2")
public class OAuthTokenController {

    private final RestTemplate restTemplate;
    private final Gson gson;

    public OAuthTokenController(RestTemplate restTemplate, Gson gson) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }


    @GetMapping("/token")
    public OAuthToken callback(@RequestParam String code) {

        HttpEntity<?> httpEntity = makeHttpEntity(code);
        String url = "http://localhost:8081/oauth/token";
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            OAuthToken oAuthToken = new OAuthToken();
            return gson.fromJson(response.getBody(), oAuthToken.getClass());
        }
        return null;
    }

    private HttpEntity<?> makeHttpEntity(String code) {
        String basicToken = makeCredentials();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + basicToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:8090/oauth2/token");

        return new HttpEntity<>(params, headers);
    }

    private String makeCredentials() {
        String clientId = "testClientId";
        String clientSecret = "testSecret";
        String credentials = String.format("%s:%s", clientId, clientSecret);
        return new String(Base64.encodeBase64(credentials.getBytes()));
    }

}
