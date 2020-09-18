package com.cn.responseentitydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResponseEntityDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResponseEntityDemoApplication.class, args);
        System.out.println("启动成功");
    }

}
