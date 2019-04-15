package com.cn.validationdemo.controller;

import com.cn.validationdemo.entity.ValidationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yc
 */
@RestController
public class ValidationController {

    /**
     * 请求头或体参数
     */
    @PostMapping("test1")
    public ResponseEntity<Object> test1(@Validated ValidationModel validationModel) {
        return ResponseEntity.ok(validationModel);
    }

    /**
     * 请求json
     */
    @PostMapping("test2")
    public ResponseEntity<Object> test2(@Validated @RequestBody ValidationModel validationModel) {
        return ResponseEntity.ok(validationModel);
    }

}
