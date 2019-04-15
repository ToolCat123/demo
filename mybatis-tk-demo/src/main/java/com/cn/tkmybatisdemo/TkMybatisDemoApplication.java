package com.cn.tkmybatisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.cn.tkmybatisdemo.mapper")// tk_mybatis的注解
public class TkMybatisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkMybatisDemoApplication.class, args);
    }

}
