package com.cn.jpademo.service;

import com.cn.jpademo.entity.Tenant;

/**
 * <p>
 * 企业 服务类
 * </p>
 *
 * @author yc
 */
public interface TenantService {

    /**
     * 根据主键查询
     * @param id 主键id
     * @return 表实体
     */
    Tenant getById(Long id);
}
