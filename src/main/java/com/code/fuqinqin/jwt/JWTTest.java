package com.code.fuqinqin.jwt;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.badger.common.lang.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class JWTTest {
    private static final String SECRET = "12345";
    private static final String CHARSET = "UTF-8";

    public static void main(String[] args) throws UnsupportedEncodingException {
        // 生成token
        String token = generateToken();

        // 篡改token信息
//        token = tampering(token);

        // 解密token
        decodeToken(token);
    }

    private static String tampering(String token) throws UnsupportedEncodingException {
        token = new String(Base64.getDecoder().decode(token.getBytes(CHARSET)), CHARSET);
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        JSONObject jsonObject = JSONObject.parseObject(new String(Base64.getDecoder().decode(decodedJWT.getPayload().getBytes(CHARSET)), CHARSET));
        jsonObject.put("one", 2);
        token = StringUtil.concat_ws(".", decodedJWT.getHeader(), Base64.getEncoder().encodeToString(jsonObject.toJSONString().getBytes(CHARSET)), decodedJWT.getSignature());
        System.out.println("篡改后的token = " + token);
        token = Base64.getEncoder().encodeToString(token.getBytes(CHARSET));
        return token;
    }

    private static void decodeToken(String token) throws UnsupportedEncodingException {
        token = new String(Base64.getDecoder().decode(token.getBytes(CHARSET)), CHARSET);
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("token被篡改...");
            return;
        }
        String header = decodedJWT.getHeader();
        String payload = decodedJWT.getPayload();
        String signature = decodedJWT.getSignature();
        String decodeToken = decodedJWT.getToken();
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println("header = " + header);
        System.out.println("payload = " + payload);
        System.out.println("signature = " + signature);
        System.out.println("decodeToken = " + decodeToken);
        System.out.println("claims = " + JSONObject.toJSONString(claims));
        System.out.println("one = " + decodedJWT.getClaim("one").asInt());
        System.out.println("one = " + claims.get("one").asInt());
    }

    private static String generateToken() throws UnsupportedEncodingException {
        // 生成过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 60);
        Date expiresDate = nowTime.getTime();

        // 创建JWT的header信息
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        // 创建JWT
        String token = JWT.create().withHeader(headerMap)
                .withClaim("iss", "fuqinqin")
                .withClaim("aud", "All")
                .withClaim("one", 1)
                .withClaim("two", 2)
                .withClaim("three", 3)
                .withIssuedAt(new Date())
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));

        System.out.println("token before encode = " + token);

        token = Base64.getEncoder().encodeToString(token.getBytes(CHARSET));
        System.out.println("token after encode = " + token);

        return token;
    }
}
