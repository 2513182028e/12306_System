package com.java.train.common.util;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.java.train.common.resp.MemberLoginResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Map;


public class JwtUtil {

    private static final Logger LOG= LoggerFactory.getLogger(JwtUtil.class);
    /**
     * 盐值很重要，不能泄露，且每个项目都应该不一样可以放到配置文件中去
     */
    private  static final String key="Java12306";

    public static String createToken(Map<String,String> map)
    {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        return builder
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(key.getBytes()));
    }

    public static  boolean verify(String token)
    {
          try {
              JWT.require(Algorithm.HMAC256(key.getBytes())).build().verify(token);
              return true;
          }catch (Exception e)
          {
              return false;
          }
    }

    public static DecodedJWT getTokenInfo(String token)
    {
        return  JWT.require(Algorithm.HMAC256(key.getBytes())).build().verify(token);
    }

    public static MemberLoginResp getObject(String token) {
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(key.getBytes())).build().verify(token);
        Long id = jwt.getClaim("id").asLong();
        String mobile=jwt.getClaim("mobile").asString();
        return new MemberLoginResp(id,mobile,token);
    }





}
