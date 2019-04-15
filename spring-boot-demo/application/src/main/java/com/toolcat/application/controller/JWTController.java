package com.toolcat.application.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JWTController {

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String admin() {
        return "this is admin api";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') AND hasRole('USER')")
    public String user() {
        return "this is user api";
    }

    @GetMapping("/guest")
    @PostAuthorize("returnObject!=null &&  returnObject != authentication.name")
    public String guest() {
        return "this is guest api";
    }

}
