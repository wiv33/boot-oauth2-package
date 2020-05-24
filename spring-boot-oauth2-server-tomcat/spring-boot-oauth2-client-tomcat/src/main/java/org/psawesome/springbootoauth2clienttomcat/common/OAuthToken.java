package org.psawesome.springbootoauth2clienttomcat.common;

import lombok.Getter;
import lombok.Setter;

/**
 * package: org.psawesome.springbootoauth2clienttomcat.common
 * author: PS
 * DATE: 2020-05-22 금요일 20:54
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
