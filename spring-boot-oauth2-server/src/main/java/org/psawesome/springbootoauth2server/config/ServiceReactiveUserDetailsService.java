package org.psawesome.springbootoauth2server.config;

import lombok.RequiredArgsConstructor;
import org.psawesome.springbootoauth2server.user.MyUser;
import org.psawesome.springbootoauth2server.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * package: org.psawesome.springbootoauth2server.config
 * author: PS
 * DATE: 2020-06-06 토요일 23:22
 */
@RequiredArgsConstructor
public class ServiceReactiveUserDetailsService implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {
  private final UserService users;

  @Override
  public Mono<UserDetails> findByUsername(String username) {
    return this.users.findByEmail(username)
            .map(CustomUser::new);
  }

  @Override
  public Mono<UserDetails> updatePassword(UserDetails user, String newPassword) {
    return this.users.findByEmail(user.getUsername())
            .doOnSuccess(u -> u.)
            .map(this::create);
  }

  private Mono<UserDetails> create(MyUser user) {

  }

  private class CustomUser extends User {

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
      super(username, password, authorities);
    }


  }
}
