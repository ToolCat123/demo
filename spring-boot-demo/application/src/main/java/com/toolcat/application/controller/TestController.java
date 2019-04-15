package com.toolcat.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * TestController
 */
@Controller
public class TestController {

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/test1")
    @ResponseBody
    public String test1() {
        return "TestController/test1";
    }

    @GetMapping("/test2")
    @ResponseBody
    public String test2(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

}
