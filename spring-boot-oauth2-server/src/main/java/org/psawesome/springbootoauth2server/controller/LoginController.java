package org.psawesome.springbootoauth2server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * package: org.psawesome.springbootoauth2server.controller
 * author: PS
 * DATE: 2020-06-06 토요일 23:03
 */
@Controller
public class LoginController {
  @GetMapping("/login")
  public Mono<String> login() {
    return Mono.just("login");
  }
}
