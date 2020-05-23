package org.psawesome.clientjdbc.common;

import lombok.Getter;
import lombok.Setter;

/**
 * package: org.psawesome.clientjdbc.common
 * author: PS
 * DATE: 2020-05-23 토요일 15:54
 */
@Getter
@Setter
public class OAuthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expries_in;
    private String scope;
}

