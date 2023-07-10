package com.liao.utils;

import com.alibaba.fastjson.JSON;
import com.liao.model.entity.query.SysUserQuery;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @BelongsPackage: com.liao.utils
 * @Author: LiaoYunYang
 * @CreateTime: 2023-03-08  00:02
 * @Version: 1.0
 */
public class JwtUtil {

    // 有效期为
    public static final Long JWT_TTL = 60 * 60 * 1000L;   // 60 * 60 * 1000 为一个小时
    // 设置秘钥明文
    public static final String JWT_KEY = "liaomou";


    /**
     * 获取一个UUID(全文大写没有分割线)
     *
     * @return
     */
    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 生成jtw
     *
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * jwt生成器
     *
     * @param subject   需要放置的数据
     * @param ttlMillis 超时时间
     * @param uuid      随机字符串
     * @return
     */
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)                // 唯一的ID
                .setSubject(subject)        // 主题  可以是JSON数据
                .setIssuer(JWT_KEY)         // 签发者
                .setIssuedAt(now)           // 签发时间
                .signWith(signatureAlgorithm, secretKey)    // 使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }


    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) throws Exception {
        // 生成token
        HashMap<String, String> userMap = new HashMap<>();
        userMap.put("id", "2");
        userMap.put("userName", "dsadas");
        String jwt = JwtUtil.createJWT(JSON.toJSONString(userMap));

        System.out.println(jwt);

        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0NDA4YWNkNzZkNGI0NzQ2OWZiYTM4NzhlYTc3NGI4YyIsInN1YiI6IntcImlkXCI6XCIyNFwiLFwidXNlck5hbWVcIjpcImxpYW9tb3VcIn0iLCJpc3MiOiJsaWFvbW91IiwiaWF0IjoxNjg4NDU1NzAxLCJleHAiOjE2ODg0NTkzMDF9.sbsvF4_stuTnslzunldPe9mohqpBK39O0KOfgQ6iU9g");

        String subject = claims.getSubject();
        SysUserQuery query = JSON.parseObject(subject, SysUserQuery.class);

        System.out.println(query);

    }

}