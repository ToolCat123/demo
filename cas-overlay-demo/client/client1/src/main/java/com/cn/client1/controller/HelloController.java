package com.cn.client1.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yc
 * @date 2020/9/10
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication.getAuthorities() = " + authentication.getAuthorities());
        System.out.println("authentication.getDetails() = " + authentication.getDetails());
        System.out.println("authentication.getName() = " + authentication.getName());
        System.out.println("authentication.getPrincipal() = " + authentication.getPrincipal());
        System.out.println("authentication.getCredentials() = " + authentication.getCredentials());
        return "hello";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "user";
    }
}
