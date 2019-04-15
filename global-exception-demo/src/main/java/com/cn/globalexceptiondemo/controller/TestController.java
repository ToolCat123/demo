package com.cn.globalexceptiondemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

/**
 * @author yc
 */
@RestController
public class TestController {

    @GetMapping("test1")
    public ResponseEntity<Object> test1() {
        int i = 1 / 0;
        return ResponseEntity.ok("操作成功");
    }

    @GetMapping("test2")
    public ResponseEntity<Object> test2() {
        try {
            throw new SQLException("SQL异常","500");
        } catch (SQLException ignored) {
        }
        return ResponseEntity.ok("操作成功");
    }
}
