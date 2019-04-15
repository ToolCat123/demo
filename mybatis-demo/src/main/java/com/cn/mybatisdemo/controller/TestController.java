package com.cn.mybatisdemo.controller;

import com.cn.mybatisdemo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yc
 */
@RestController
public class TestController {
    @Autowired
    private TenantService tenantService;

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tenantService.getById(id));
    }
}
