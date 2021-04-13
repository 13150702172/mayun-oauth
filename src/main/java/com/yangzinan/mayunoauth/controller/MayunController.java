package com.yangzinan.mayunoauth.controller;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.yangzinan.mayunoauth.config.MayunConfig;
import com.yangzinan.mayunoauth.entity.Oauth;
import com.yangzinan.mayunoauth.service.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MayunController {

    private Logger log = LoggerFactory.getLogger(MayunController.class);

    @Autowired
    private OAuthService oAuthService;

    /**
     * 登录
     * @param request
     * @param response
     */
    @GetMapping("/login/oauth")
    public void login(HttpServletRequest request, HttpServletResponse response){
        log.info("第三方登录开始");
            try {
                //重定向到授权界面
                response.sendRedirect(MayunConfig.MAYUN_UI);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * 回调函数，获取code
     * @param code
     * @return
     */
    @GetMapping("/login/callback")
    public Map<String,Object> login(@RequestParam("code") String code){
        log.info("回调函数");
        //使用code获取token
        String token = oAuthService.getToken(code);
        JSONObject parse = (JSONObject) JSONObject.parse(token);
        //将token返回值，转换为Oauth对象
        Oauth oauth = JSONObject.toJavaObject(parse, Oauth.class);
        //获取用户信息
        Map<String, Object> userInfo = oAuthService.getUserInfo(oauth.getAccessToken());

        //返回值
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","登录成功");
        result.put("data",userInfo);

        return result;
    }

}
