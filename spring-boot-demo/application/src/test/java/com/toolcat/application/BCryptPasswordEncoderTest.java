package com.toolcat.application;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPasswordEncoderTest {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void test0() {
        System.out.println(passwordEncoder.encode("secret"));
    }

    // 密码加密解密
    @Test
    void test1() {

        String psw = "123456";
        // 加密
        String encode = passwordEncoder.encode(psw);
        System.out.println(encode);
        // 匹配
        boolean matches = passwordEncoder.matches(psw, encode);
        System.out.println(matches);
        // 是否需要重新编码(一般不用,一般返回false)
        boolean encoding = passwordEncoder.upgradeEncoding(encode);
        System.out.println(encoding);

    }

}
