package com.cn.validationdemo.controller;

import com.cn.validationdemo.entity.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author yc
 */
@RestController
@Validated
public class QueryController {

    @GetMapping("test1/{name}/{age}")
    public ResponseEntity<Object> test1(@PathVariable("name") String name,
                                        @PathVariable("age") Long age) {
        return ResponseEntity.ok(name + ":" + age);
    }

    /**
     * 获取get或post请求头参数
     */
    @GetMapping("test2/{birthday}")
    public ResponseEntity<Object> test2(@PathVariable("birthday") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date birthday) {
        return ResponseEntity.ok(birthday);
    }

    /**
     * 获取post请求体
     */
    @PostMapping("test3")
    public ResponseEntity<Object> test3(Query query) {
        return ResponseEntity.ok(query);
    }

    /**
     * 获取post的json请求参数
     */
    @PostMapping("test4")
    public ResponseEntity<Object> test4(@RequestBody Query query) {
        return ResponseEntity.ok(query);
    }

}
