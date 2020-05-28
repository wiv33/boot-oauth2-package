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
import org.springframework.web.bind.annotation.*;
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

    String url = "http://localhost:8081/oauth/token";

    public OAuthTokenController(RestTemplate restTemplate, Gson gson) {
        this.restTemplate = restTemplate;
        this.gson = gson;
    }

    @GetMapping("/token/refresh")
    public OAuthToken refreshToken(@RequestParam String refreshToken) {
        HttpEntity<?> httpEntity = makeRefreshHttpEntity(makeCredentials(), refreshToken);
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            OAuthToken oAuthToken = new OAuthToken();
            return gson.fromJson(response.getBody(), oAuthToken.getClass());
        }

        return null;
    }

    private HttpEntity<?> makeRefreshHttpEntity(String credentials, String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + credentials);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("refresh_token", refreshToken);
        params.add("grant_type", "refresh_token");

        return new HttpEntity<>(params, headers);
    }

    @GetMapping("/token")
    public OAuthToken callback(@RequestParam String code) {
        return getOAuthToken(code);
    }

    private OAuthToken getOAuthToken(@RequestParam String code) {
        HttpEntity<?> httpEntity = makeHttpEntity(code);
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            OAuthToken oAuthToken = new OAuthToken();
            return gson.fromJson(response.getBody(), oAuthToken.getClass());
        }
        return null;
    }

    @PostMapping("/callback")
    public OAuthToken callbackPost(@RequestParam String code) {
        return getOAuthToken(code);
    }

    private HttpEntity<?> makeHttpEntity(String code) {
        String basicToken = makeCredentials();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Basic " + basicToken);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "https://www.getpostman.com/oauth2/callback");

        return new HttpEntity<>(params, headers);
    }

    private String makeCredentials() {
        String clientId = "testClientId";
        String clientSecret = "testSecret";
        String credentials = String.format("%s:%s", clientId, clientSecret);
        return new String(Base64.encodeBase64(credentials.getBytes()));
    }

}
