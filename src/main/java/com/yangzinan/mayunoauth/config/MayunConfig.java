package com.yangzinan.mayunoauth.config;

/**
 * 码云相关配置文件
 */
public class MayunConfig {

    //客户端id,从码云中获取，需要自己补充
    public static final String CLIENT_ID = "";

    //客户端密钥，需要自己补充
    public static final String CLIENT_SECRET = "";

    //回调地址
    public static final String CALL_BACK = "http://localhost:9000/login/callback";

    //码云授权界面
    public static final String MAYUN_UI = "https://gitee.com/oauth/authorize?client_id=" + CLIENT_ID + "&redirect_uri="+CALL_BACK+"&response_type=code";;

    //获取token地址
    public static final String GET_TOKEN_URL = "https://gitee.com/oauth/token";

    // 获取用户信息接口
    public static final String GET_USERINFO = "https://gitee.com/api/v5/user";

}
