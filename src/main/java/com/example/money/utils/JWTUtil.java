package com.example.money.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.money.filter.TokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzx
 * @date 2023-02-11 21:51
 * @description
 */
public class JWTUtil {
    //生成token的方法

    public static Map<String,Object> createToken(String openid,String secret){
        Map<String,Object > head=new HashMap<>();
        head.put("alg","HS256");
        head.put("typ","jwt");
//        Date date=new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR,24);
        Date date=calendar.getTime();
        System.out.println(date);
        String token= JWT.create()
                .withExpiresAt(date)
                .withClaim("openid",openid)
                .sign(Algorithm.HMAC256(secret));
//        System.out.println(token);
        Map<String,Object> map=new HashMap<>();
        map.put("token",token);
//        SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        map.put("etime",date);
        return map;
    }

    /**
     * token 验证 解析
     * @param token
     * @return
     */
    public static String verifyToken(String token, String secret) throws TokenException {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);
                String openid = jwt.getClaim("openid").toString();
                return openid;
    }
}
