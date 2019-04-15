package com.cn.aliyunlogdemo.controller;

import com.aliyun.openservices.log.exception.LogException;
import com.cn.aliyunlogdemo.service.MyLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yc
 */
@RestController
public class TestController {

    @Autowired
    private MyLogService myLogService;

    /**
     * 添加日志
     */
    @GetMapping("/write")
    public void write() {
        this.myLogService.writeLog();
    }

    /**
     * 获取数据
     */
    @GetMapping("/read")
    public void read() throws LogException {
        this.myLogService.readLog();
    }

}
