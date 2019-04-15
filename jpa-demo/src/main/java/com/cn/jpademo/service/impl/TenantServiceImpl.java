package com.cn.jpademo.service.impl;

import com.cn.jpademo.entity.Tenant;
import com.cn.jpademo.repository.TenantRepository;
import com.cn.jpademo.service.TenantService;
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
    private TenantRepository tenantRepository;

    @Override
    public Tenant getById(Long id) {
        return tenantRepository.getById(id);
    }
}
