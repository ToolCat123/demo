package com.cn.websocketdemo.controller;

import com.cn.websocketdemo.service.WebSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author yc
 * @date 2021/5/29
 */
@RestController
@RequestMapping("/api/ws")
public class WebSocketController {

    /**
     * 群发消息内容
     *
     * @param message 消息
     */
    @GetMapping(value = "/sendAll")
    public String sendAllMessage(@RequestParam String message) {
        try {
            WebSocketServer.broadCastInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    /**
     * 指定会话ID发消息
     *
     * @param message 消息内容
     * @param id      连接会话ID
     */
    @GetMapping(value = "/sendOne")
    public String sendOneMessage(@RequestParam String message, @RequestParam String id) {
        try {
            WebSocketServer.sendMessage(message, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
