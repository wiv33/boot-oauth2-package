package org.psawesome.springbootoauth2tomcatjdbc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.config
 * author: PS
 * DATE: 2020-05-23 토요일 16:43
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final long MAX_AGE_SECONDS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECONDS);
    }

}
