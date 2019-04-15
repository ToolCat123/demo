package com.cn.mybatisdemo.service.impl;

import com.cn.mybatisdemo.entity.Tenant;
import com.cn.mybatisdemo.mapper.TenantMapper;
import com.cn.mybatisdemo.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Tenant getById(Long id) {
        return tenantMapper.selectByPrimaryKey(id);
    }
}
