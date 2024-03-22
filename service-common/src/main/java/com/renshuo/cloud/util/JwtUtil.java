package com.renshuo.cloud.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

/** 
* @description: jwt工具类
*
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
        String secret ="123456";
        String token = createJwt("1",secret,-1);
        System.out.println(token);
        String id = getSubject(token,secret);
        System.out.println(id);
    }

    /**
     * 构建jwt,获取创建token字符串
     *
     * @param subject      主题信息，可以是JSON数据
     * @param secret       加密秘钥
     * @param expireSecond 超时时间
     * @return
     */
    public static String createJwt(String subject, String secret, long expireSecond) throws Exception {
        try {
            long nowMillis = System.currentTimeMillis();
            Date nowDate = new Date(nowMillis);
            Algorithm algorithm = Algorithm.HMAC256(secret);


            JWTCreator.Builder jwtBuilder = JWT.create()
                    .withSubject(subject)
                    .withIssuer("sg")
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
        } catch (Exception e) {
            log.error("subject: {} 生成jwt异常:{}", subject, e.getMessage(), e);
            throw new Exception("生成jwt异常:" + e.getMessage());
        }
    }

    /**
     * 构建app jwt
     *
     * @param appId        应用ID
     * @param secret       加密秘钥
     * @param expireSecond 超时时间
     * @return
     */
    public static String createApiJwt(Long appId, String secret, long expireSecond) throws Exception {
        try {
            long nowMillis = System.currentTimeMillis();
            Date nowDate = new Date(nowMillis);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTCreator.Builder jwtBuilder = JWT.create().withClaim("appId", appId)
                    .withSubject("api")
                    .withAudience("api")
                    .withIssuer(String.valueOf(appId))
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
            }
            return jwtBuilder.sign(algorithm);
        } catch (Exception e) {
            log.error("appId: {} 生成jwt异常:{}", appId, e.getMessage(), e);
            throw new Exception("生成jwt异常:" + e.getMessage());
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
     * 从jwtToken中获取对象信息
     *
     * @param token
     * @param secret
     * @return
     */
    public static Long getClaimValueToLong(String token, String secret, String claimName) {
        return parseJWT(token, secret).getClaim(claimName).asLong();
    }

    /**
     * 从jwtToken中获取对象信息
     *
     * @param token
     * @param secret
     * @return
     */
    public static String getClaimValue(String token, String secret, String claimName) {
        return parseJWT(token, secret).getClaim(claimName).asString();
    }
    /**
     * 从jwtToken中获取对象信息
     *
     * @param token
     * @param secret
     * @return
     */
//    public static User getUserInfo(String token, String secret) {
//        return parseJWT(token, secret).getClaim("userInfo").as(User.class);
//    }

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
