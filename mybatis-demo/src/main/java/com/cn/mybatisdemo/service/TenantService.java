package com.cn.mybatisdemo.service;

import com.cn.mybatisdemo.entity.Tenant;

/**
 * <p>
 * 企业 服务类
 * </p>
 *
 * @author yc
 */
public interface TenantService {

    Tenant getById(Long id);
}
