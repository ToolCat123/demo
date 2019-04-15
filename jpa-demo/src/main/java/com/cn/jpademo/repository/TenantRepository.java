package com.cn.jpademo.repository;

import com.cn.jpademo.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yc
 */
public interface TenantRepository extends JpaRepository<Tenant,String> {
    /**
     * 根据tenantId查询
     * @param id 主键id
     * @return 表实体
     */
    Tenant getById(Long id);
}
