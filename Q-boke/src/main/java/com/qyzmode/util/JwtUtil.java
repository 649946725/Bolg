package com.qyzmode.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {

    //加密Token的密钥
    private static final String TOKEN_SERET = "649946725@qq.com";

    /**
     * 用jwt生成token
     */
    public static String getToken(Map<String, String> map) {
        //用Calendar工具类生成一个默认的token过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);//设置token的过期时间为7天
        //用jwt创造token
        JWTCreator.Builder builder = JWT.create();
        //
        map.forEach((k, v) -> {
              builder.withClaim(k,v);
        });
        String token=builder
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(TOKEN_SERET));
        return token;

    }

    /**
     * 验证token
     */
}
