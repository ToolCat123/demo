package com.toolcat.application.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * session demo
 */
@RestController
public class SessionController {

    private static final String USER_SESSION = "USER_SESSION";

    @GetMapping("/set_session")
    public String steSession(HttpSession session, Authentication authentication) {
        session.setAttribute(USER_SESSION, "this is session value: " + authentication.getName());
        return authentication.getName();
    }

    @GetMapping("/get_session")
    public String getSession(HttpSession session) {
        return session.getAttribute(USER_SESSION).toString();
    }

    @GetMapping("/invalidate")
    public String invalidate(HttpSession session) {
        session.invalidate();// 取消session
        return "session取消成功";
    }

    @GetMapping("/get_cookie")
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            return Arrays.stream(cookies)
                    .map(cookie -> cookie.getName() + ": " + cookie.getValue()).collect(Collectors.joining(","));
        }
        return "获取cookie错误";
    }


}
