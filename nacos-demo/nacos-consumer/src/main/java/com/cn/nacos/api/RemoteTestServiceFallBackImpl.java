package com.cn.nacos.api;

import org.springframework.stereotype.Component;

/**
 * @author yc
 */
@Component
public class RemoteTestServiceFallBackImpl implements RemoteTestService {

    @Override
    public String getConfig() {
        return "远程调用访问异常";
    }
}
