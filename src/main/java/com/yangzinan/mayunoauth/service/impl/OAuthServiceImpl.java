package com.yangzinan.mayunoauth.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.yangzinan.mayunoauth.config.MayunConfig;
import com.yangzinan.mayunoauth.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OAuthServiceImpl implements OAuthService {

    private Logger log  = LoggerFactory.getLogger(OAuthServiceImpl.class);

    /**
     * 通过code获取token
     * @param code
     * @return
     */
    @Override
    public String getToken(String code) {
        log.info("获取token");
        //以下为必要的参数
        Map<String,Object> params = new HashMap<>();
        params.put("grant_type","authorization_code");
        params.put("code",code);
        params.put("client_id", MayunConfig.CLIENT_ID);
        params.put("redirect_uri",MayunConfig.CALL_BACK);
        params.put("client_secret",MayunConfig.CLIENT_SECRET);
        //http请求
        String post = HttpUtil.post(MayunConfig.GET_TOKEN_URL, params);
        log.info("获取到的token:{}",post);
        return post;
    }

    /**
     * 通过token获取用户信息
     * @param token
     * @return
     */
    @Override
    public Map<String, Object> getUserInfo(String token) {
        Map<String,Object> params = new HashMap<>();
        params.put("access_token",token);
        //http请求
        String userInfo = HttpUtil.get(MayunConfig.GET_USERINFO, params);
        Map<String, Object> res  = (Map<String, Object>) JSONObject.parse(userInfo);
        return res;
    }
}
