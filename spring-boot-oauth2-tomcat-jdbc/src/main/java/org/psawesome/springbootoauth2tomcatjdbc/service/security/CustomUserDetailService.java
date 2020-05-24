package org.psawesome.springbootoauth2tomcatjdbc.service.security;

import lombok.RequiredArgsConstructor;
import org.psawesome.springbootoauth2tomcatjdbc.entity.MyUser;
import org.psawesome.springbootoauth2tomcatjdbc.repo.MyUserJpaRepo;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.service.security
 * author: PS
 * DATE: 2020-05-23 토요일 19:38
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MyUserJpaRepo repo;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = repo.findByUid(username).orElseThrow(() -> new UsernameNotFoundException("not exists user"));
        detailsChecker.check(user);
        return user;
    }
}
