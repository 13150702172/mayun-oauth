package com.yangzinan.mayunoauth.service;

import java.util.Map;

public interface OAuthService {
    /**
     * 通过code获取token
     * @param code
     * @return
     */
    public  String getToken(String code);

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    public Map<String,Object> getUserInfo(String token);
}
