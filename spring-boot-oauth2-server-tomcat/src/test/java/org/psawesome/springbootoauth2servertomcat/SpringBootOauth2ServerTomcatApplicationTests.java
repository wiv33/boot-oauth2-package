package org.psawesome.springbootoauth2servertomcat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootOauth2ServerTomcatApplicationTests {

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    SpringBootOauth2ServerTomcatApplication application;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .build();
        MockitoAnnotations.initMocks(SpringBootOauth2ServerTomcatApplication.class);
    }

    @Test
    void contextLoads() {
        assertNotNull(testRestTemplate);
        assertNotNull(application);
    }

    @Test
    void testToken() {
        LinkedMultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        map.add("client_id", "testClientId");
        map.add("redirect_uri", "http://localhost:8081/oauth2/callback");
        map.add("response_type", "code");
        map.add("scope", "read");
        HttpEntity<?> httpEntity = new HttpEntity<>(map);

        ResponseEntity<String> response = testRestTemplate.postForEntity("http://localhost:8081/oauth/authorize", httpEntity, String.class);
        assertEquals(response.getStatusCode(), HttpStatus.FOUND);

        HttpHeaders responseHeaders = new HttpHeaders(response.getHeaders());
        assertEquals(responseHeaders.getLocation(), URI.create("http://localhost:8081/login"));


    }

    @Test
    void testPerform() throws Exception {
        MockHttpServletResponse mvcResult = mockMvc.perform(
                post("http://localhost:8081/oauth/authorize")
                        .header("client_id", "testClientId")
                        .header("redirect_uri", "http://localhost:8081/oauth2/callback")
                        .header("response_type", "code")
                        .header("scope", "read")
        )
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse();

        assertNull(mvcResult.getRedirectedUrl());

        assertEquals(mvcResult.getForwardedUrl(), "/oauth/error");

        assertNull(mvcResult.getContentType());

        assertEquals(0, mvcResult.getContentLength());
    }

    @Test
    void testMock() {
        // 테스트 어떻게 하지?
    }
}
