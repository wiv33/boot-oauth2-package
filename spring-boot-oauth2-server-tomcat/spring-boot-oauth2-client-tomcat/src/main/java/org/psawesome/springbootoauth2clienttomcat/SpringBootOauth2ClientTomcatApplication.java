package org.psawesome.springbootoauth2clienttomcat;

import com.google.gson.Gson;
import org.apache.tomcat.util.codec.binary.Base64;
import org.psawesome.springbootoauth2clienttomcat.common.OAuthToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringBootOauth2ClientTomcatApplication {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootOauth2ClientTomcatApplication.class, args);
    }

    @RestController
    @RequestMapping("/oauth2")
    static class OAuthTokenController {
        private final Gson gson;
        private final RestTemplate restTemplate;

        OAuthTokenController(Gson gson, RestTemplate restTemplate) {
            this.gson = gson;
            this.restTemplate = restTemplate;
        }

        @GetMapping("/token")
        public OAuthToken callback(@RequestParam String code) {
            String encodeCredentials = makeCredentials();

            HttpEntity<Object> request = makeEntity(code, encodeCredentials);

            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8081/oauth/token", request, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                OAuthToken oAuthToken = new OAuthToken();
                return gson.fromJson(response.getBody(), oAuthToken.getClass());
            }

            return null;
        }

        private HttpEntity<Object> makeEntity(@RequestParam String code, String encodeCredentials) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("Authorization", "Basic " + encodeCredentials);

            LinkedMultiValueMap<Object, Object> multiMap = new LinkedMultiValueMap<>();
            multiMap.add("code", code);
            multiMap.add("grant_type", "authorization_code");
            multiMap.add("redirect_uri", "http://localhost:8081/oauth2/callback");

            return new HttpEntity<>(multiMap, headers);
        }

        private String makeCredentials() {
            var clientId = "testClientId";
            var secret = "testSecret";
            var credentials = String.format("%s:%s", clientId, secret);
            return new String(Base64.encodeBase64(credentials.getBytes()));
        }
    }
}
