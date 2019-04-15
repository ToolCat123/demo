package com.toolcat.application.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie demo
 */
@RestController
public class CookieController {

    @GetMapping("/write")
    public String writeCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("MyCookieName", "cookieValue");
//        cookie.setPath("/write"); // 只有在write目录下才能看到cookie
//        cookie.setMaxAge(0); // cookie的过期时间
        cookie.setHttpOnly(true); // 表示只有该项目才能访问
        cookie.setSecure(false); // true只有https请求才能看到cookie
        response.addCookie(cookie);
        return "ok";
    }

    @GetMapping("/read")
    public String readCookie(@CookieValue String MyCookieName) {
        return MyCookieName;
    }

}
