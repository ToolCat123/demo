package com.cn.aliyunlogdemo.service;

import com.aliyun.openservices.log.exception.LogException;

/**
 * @author yc
 */
public interface MyLogService {

    /**
     * 写日志
     */
    void writeLog();


    /**
     * 保存日志
     */
    void readLog() throws LogException;

}
