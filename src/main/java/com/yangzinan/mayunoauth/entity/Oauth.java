package com.yangzinan.mayunoauth.entity;

import lombok.Data;

@Data
public class Oauth {

    private String accessToken;

    private String tokenType;

    private Long expiresIn;

    private String refreshToken;

    private String scope;

    private String createdAt;
}
