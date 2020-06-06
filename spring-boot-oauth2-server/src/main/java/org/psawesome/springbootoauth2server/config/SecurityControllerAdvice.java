package org.psawesome.springbootoauth2server.config;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.reactive.result.view.CsrfRequestDataValueProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * package: org.psawesome.springbootoauth2server.config
 * author: PS
 * DATE: 2020-06-06 토요일 22:48
 */
@ControllerAdvice
public class SecurityControllerAdvice {

  @ModelAttribute
  Mono<CsrfToken> csrfToken(ServerWebExchange exchange) {
    Mono<CsrfToken> csrfToken = exchange.getAttribute(CsrfToken.class.getName());
    if (Objects.isNull(csrfToken)) return Mono.empty();
    return csrfToken.doOnSuccess(token -> {
      exchange.getAttributes()
              .put(CsrfRequestDataValueProcessor.DEFAULT_CSRF_ATTR_NAME, token);
    });
  }

  @ModelAttribute("currentUser")
  Mono<User> currentUser(@AuthenticationPrincipal Mono<User> currentUser) {
    return currentUser;
  }
}
