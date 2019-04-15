package com.cn.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yc
 */
@RestController
public class TestController {

    @Value("${student.name:张三}")
    private String name;

    @Value("${student.age:18}")
    private Integer age;

    @GetMapping("/get_config")
    public String getConfig() {
        return name + " " + age;
    }

}
