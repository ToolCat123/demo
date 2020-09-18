package com.cn.responseentitydemo.controller;


import com.cn.responseentitydemo.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author yc
 * @date 2020/9/17
 */
@RestController
public class TestController {

    /**
     * Get请求时
     */
    @GetMapping("find/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") Long id) {
        User user = new User(id, "张三");
        return ResponseEntity.ok(user);
    }

    /**
     * Post请求新增一条记录,有返回值
     */
    @PostMapping("save1")
    public ResponseEntity<Long> save1(@RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user.getId());
    }

    /**
     * Post请求新增一条记录,无返回值
     */
    @PostMapping("save2")
    public ResponseEntity save2(@RequestBody User user) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Delete删除请求,有返回值
     */
    @DeleteMapping("delete1/{id}")
    public ResponseEntity<Long> delete1(@PathVariable("id") Long id) {
        return ResponseEntity.ok(id);
    }

    /**
     * Delete删除请求,无返回值
     */
    @DeleteMapping("delete2/{id}")
    public ResponseEntity delete2(@PathVariable("id") Long id) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Put修改请求,有返回值
     */
    @PutMapping("update1")
    public ResponseEntity<Long> update1(@RequestBody User user) {
        return ResponseEntity.ok(user.getId());
    }

    /**
     * Put修改请求,无返回值
     */
    @PutMapping("update2")
    public ResponseEntity update2(@RequestBody User user) {
        return ResponseEntity.noContent().build();
    }


}
