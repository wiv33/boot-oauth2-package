package org.psawesome.springbootoauth2tomcatjdbc.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.psawesome.springbootoauth2tomcatjdbc.entity.MyUser;
import org.psawesome.springbootoauth2tomcatjdbc.repo.MyUserJpaRepo;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * package: org.psawesome.springbootoauth2tomcatjdbc.common
 * author: PS
 * DATE: 2020-05-23 토요일 17:18
 *
 * 회원 정보의 유효성 검사 구현체.
 * 회원 이메일로 비밀번호를 비교한다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder encoder;
    private final MyUserJpaRepo repo;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString(); // 입력한 패스워드
        MyUser user = repo.findByUid(name).orElseThrow(() -> new UsernameNotFoundException("not exists user"));

        if (!encoder.matches(password, user.getPassword())) throw new BadCredentialsException("invalid password");

        return new UsernamePasswordAuthenticationToken(name, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

