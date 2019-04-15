package com.cn.mybatisdemo.mapper;

import com.cn.mybatisdemo.entity.Tenant;

public interface TenantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tenant record);

    int insertSelective(Tenant record);

    Tenant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tenant record);

    int updateByPrimaryKey(Tenant record);
}