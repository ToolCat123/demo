package com.cn.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yc
 */
@SpringBootApplication
@MapperScan("com.cn.demo.mapper")
public class MyBatisPlusDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusDemoApplication.class);
    }
}
