package com.cn.demo.controller;


import com.cn.demo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 企业 前端控制器
 * </p>
 *
 * @author yc
 */
@RestController
@RequestMapping("/demo/tenant")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @GetMapping("list")
    public ResponseEntity getList() {
        return ResponseEntity.ok(tenantService.list());
    }

}

