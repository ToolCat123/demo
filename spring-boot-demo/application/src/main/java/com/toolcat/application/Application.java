package com.toolcat.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.toolcat.application.mapper") // mybatis扫描包
@EnableAsync // 开启异步方法
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
