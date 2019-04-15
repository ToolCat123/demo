package com.toolcat.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义 error page
 */
@Controller
public class MyErrorController implements ErrorController {

    private static Logger log = LoggerFactory.getLogger(MyErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        log.info("进入异常跳转");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        switch (statusCode) {
            case 401:
                log.info("404异常跳转");
                return "/error/401";
            case 403:
                log.info("403异常跳转");
                return "/error/403";
            case 404:
                log.info("403异常跳转");
                return "/error/404";
            case 500:
                log.info("500异常跳转");
                return "/error/500";
            default:
                log.info("默认异常跳转");
                return "/error/404";
        }
    }

    @Override
    public String getErrorPath() {
        return "error";
    }

}
