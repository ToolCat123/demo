package com.toolcat.application.config.security;

/**
 * security 常量
 */
public class MySecurityConstants {

    public static final String AUTH_LOGIN_URL = "/api/token";
    public static final String JWT_SECRET = "MTIuMy4uNC41MzRALmZzbGRramxz5LiNKiZeJiYlJiQpKCkjJCkjJA==";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    private MySecurityConstants() throws IllegalAccessException {
        throw new IllegalAccessException("Cannot create instance of static util class");
    }

}
