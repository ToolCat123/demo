package com.cn.tkmybatisdemo.service.impl;

import com.cn.tkmybatisdemo.entity.Tenant;
import com.cn.tkmybatisdemo.mapper.TenantMapper;
import com.cn.tkmybatisdemo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 企业 服务实现类
 * </p>
 *
 * @author yc
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public List<Tenant> list() {
        return tenantMapper.selectAll();
    }
}
