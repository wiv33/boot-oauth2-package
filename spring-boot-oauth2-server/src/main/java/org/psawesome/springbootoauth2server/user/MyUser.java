package org.psawesome.springbootoauth2server.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * package: org.psawesome.springbootoauth2server.user
 * author: PS
 * DATE: 2020-06-07 일요일 00:07
 */
@AllArgsConstructor
@Getter
public class MyUser {
  private Long id;
  private String email, password, firstName, lastName, alias;

  public MyUser() { }
  public MyUser(MyUser user) {
    this(user.getId(), user.getEmail(), user.getPassword(), user.getFirstName(), user
            .getLastName(), user.getAlias());
  }


}
