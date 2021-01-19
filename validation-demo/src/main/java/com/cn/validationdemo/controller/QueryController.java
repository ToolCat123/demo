package com.cn.validationdemo.controller;

import com.cn.validationdemo.entity.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author yc
 */
@RestController
@Validated
public class QueryController {

    /**
     * 获取get或post请求头参数
     */
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
     * 获取get或post请求头参数
     */
    @GetMapping("test3/{ids}")
    public ResponseEntity<Object> test3(@PathVariable("ids") Long[] ids) {
        return ResponseEntity.ok(ids);
    }

    /**
     * 获取post请求体参数
     */
    @PostMapping("test4")
    public ResponseEntity<Object> test4(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(ids);
    }

    /**
     * 获取post请求体
     */
    @PostMapping("test-a")
    public ResponseEntity<Object> testA(Query query) {
        return ResponseEntity.ok(query);
    }

    /**
     * 获取post的json请求参数
     */
    @PostMapping("test-b")
    public ResponseEntity<Object> testB(@RequestBody Query query) {
        return ResponseEntity.ok(query);
    }

}
