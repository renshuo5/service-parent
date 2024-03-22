package com.renshuo.cloud.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renshuo.cloud.sys.model.TokenInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/**
 * @description: jwt工具类
 * @author: renshuo
 * @date: 2023/8/7
 */
@Slf4j
public class JwtUtil {

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @param secret       加密key
     * @return
     */
    public static DecodedJWT parseJWT(String jsonWebToken, String secret) {
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(jsonWebToken);
    }

    public static void main(String[] args) throws Exception {
        String secret = "123456";
        String token = createJwt(null, secret, -1);
        System.out.println(token);
        String id = getSubject(token, secret);
        System.out.println(id);
    }


    /**
     * 从jwtToken中获取主体 即jwtToken所有人
     *
     * @param jwtToken
     * @param secret
     * @return
     */
    public static TokenInfo getTokenInfo(String jwtToken, String secret) {
        String subject = parseJWT(jwtToken, secret).getSubject();
        TokenInfo tokenInfo = convertToTokenInfo(subject);
        return tokenInfo;
    }

    /**
     * 构建jwt,获取创建token字符串
     *
     * @param tokenInfo    主题信息，可以是JSON数据
     * @param secret       加密秘钥
     * @param expireSecond 超时时间
     * @return
     */
    public static String createJwt(TokenInfo tokenInfo, String secret, long expireSecond) {
        String ti = convertToJson(tokenInfo);
        long nowMillis = System.currentTimeMillis();
        Date nowDate = new Date(nowMillis);
        Algorithm algorithm = Algorithm.HMAC256(secret);


        JWTCreator.Builder jwtBuilder = JWT.create()
                .withSubject(ti)
                .withIssuedAt(nowDate)
                .withJWTId(UUID.randomUUID().toString());
        //添加Token过期时间
        if (expireSecond >= 0) {
            long expMillis = nowMillis + expireSecond * 1000;
            Date exp = new Date(expMillis);
            // 代表这个JWT的过期时间；
            jwtBuilder.withExpiresAt(exp)
                    // 代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
                    .withNotBefore(nowDate);
        } else {
            jwtBuilder.withNotBefore(nowDate);
        }
        return jwtBuilder.sign(algorithm);
    }


    private static String convertToJson(TokenInfo tokenInfo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(tokenInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static TokenInfo convertToTokenInfo(String subject) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(subject, TokenInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从jwtToken中获取主体 即jwtToken所有人
     *
     * @param jwtToken
     * @param secret
     * @return
     */
    public static String getSubject(String jwtToken, String secret) {
        return parseJWT(jwtToken, secret).getSubject();
    }

    /**
     * 是否已过期
     *
     * @param jwtToken
     * @param secret
     * @return
     */
    public static boolean isExpiration(String jwtToken, String secret) {
        Date expiresAt = parseJWT(jwtToken, secret).getExpiresAt();
        if (null == expiresAt) {
            return false;
        }
        return expiresAt.before(new Date());
    }
}
