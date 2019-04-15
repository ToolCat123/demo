package com.toolcat.application;

import com.toolcat.application.config.security.MySecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * jwt
 */
@Log4j2
public class JWTTest {

    // myStringKey 通过Base64生成
    private static final String myStringKey = "MTIuMy4uNC41MzRALmZzbGRramxz5LiNKiZeJiYlJiQpKCkjJCkjJA==";
    // myToken
    private static final String myToken = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJUb20ifQ.5hUTB7mY2NQyC7QxNHfTLHl3pVPe2OkoWbyJCqT-8LJ_IJlm92yyio353U1SNCWg";

    /**
     * 生成token
     */
    @Test
    public void test0() {
        // ES256算法不支持共享密钥
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        // 使用Base64解密key(在变化)
        String encodeKey = Base64.getEncoder().encodeToString(key.getEncoded());
        log.info("key={}", encodeKey);
        String token = Jwts.builder().setSubject("Tom").signWith(key).compact();
        log.info("token={}", token);
    }

    /**
     * 生成自己的token
     */
    @Test
    public void test1() {
        // 通过Base64生成自己的key字符串
//        String stringKey = Base64.getEncoder().encodeToString("12.3..4.534@.fsldkjls不*&^&&%&$)()#$)#$".getBytes());
        // stringKey=MTIuMy4uNC41MzRALmZzbGRramxz5LiNKiZeJiYlJiQpKCkjJCkjJA==
//        log.info("stringKey={}", stringKey);

        // 设置自己的key 获取自己的token
        SecretKey key = Keys.hmacShaKeyFor(myStringKey.getBytes());
        String token = Jwts.builder().setSubject("Tom").signWith(key).compact();
        // token=eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJUb20ifQ.5hUTB7mY2NQyC7QxNHfTLHl3pVPe2OkoWbyJCqT-8LJ_IJlm92yyio353U1SNCWg
        log.info("token={}", token);
    }

    /**
     * 解密token
     */
    @Test
    public void test2() {
        // key和token都必须正确,签名才能成功生成
        String subject = Jwts.parserBuilder().setSigningKey(myStringKey.getBytes()).build().parseClaimsJws(myToken).getBody().getSubject();
        assertEquals(subject, "Tom");
    }

    /**
     * token添加额外数据
     */
    @Test
    public void test3() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("该JWT的签发者")
                .setAudience(MySecurityConstants.TOKEN_AUDIENCE)
                // 初始时间
                .setIssuedAt(new Date())
                // 如果当前时间在nbf里的时间之前，则Token不被接受
                .setNotBefore(new DateTime().plusMinutes(3).toDate())
                // 过期时间
                .setExpiration(new DateTime().plusHours(1).toDate())
                .setSubject("Tom")
                .claim("rol", "Tom")
                .setClaims(new HashMap<>())
                .signWith(key).compact();
        log.info("token={}", token);
    }

}
