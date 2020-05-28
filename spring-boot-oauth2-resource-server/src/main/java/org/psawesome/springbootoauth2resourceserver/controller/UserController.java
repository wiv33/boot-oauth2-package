package org.psawesome.springbootoauth2resourceserver.controller;

import lombok.RequiredArgsConstructor;
import org.psawesome.springbootoauth2resourceserver.entity.MyUser;
import org.psawesome.springbootoauth2resourceserver.repo.UserJpaRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {
  private final UserJpaRepo repo;

  @GetMapping("/users")
  public List<MyUser> findAllUsers() {
    return repo.findAll();
  }
}
