package com.cn.nacos.api;

import com.cn.nacos.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yc
 */
@FeignClient(value = ServiceNameConstants.SERVER, fallback = RemoteTestServiceFallBackImpl.class)
public interface RemoteTestService {

    /**
     * 获取配置信息
     *
     * @return 获取配置信息
     */
    @GetMapping("/get_config")
    String getConfig();

}
