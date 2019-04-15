package com.cn.jpademo.controller;

import com.cn.jpademo.entity.TenantQuery;
import com.cn.jpademo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author yc
 */
@Validated
@RestController
public class TestController {
    @Autowired
    private TenantService tenantService;

    @GetMapping("{id:[0-9]+}")
    public ResponseEntity<Object> getById(@NotNull(message = "主键不能为空") @Max(value = 999, message = "主键最大不能超过999") @PathVariable("id") Long id) {
        return ResponseEntity.ok(tenantService.getById(id));
    }

    @PostMapping("query")
    public ResponseEntity<Object> getById(@Validated @RequestBody TenantQuery tenantQuery) {
        return ResponseEntity.ok(tenantQuery);
    }

}
