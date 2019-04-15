package com.cn.demo.service.impl;

import com.cn.demo.entity.Tenant;
import com.cn.demo.mapper.TenantMapper;
import com.cn.demo.service.TenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 企业 服务实现类
 * </p>
 *
 * @author yc
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

}
