package com.cn.websocketdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yc
 * @date 2021/5/29
 */
@ServerEndpoint(value = "/ws/asset")
@Component
public class WebSocketServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private static final AtomicInteger ONLINE_COUNT = new AtomicInteger(0);
    private static CopyOnWriteArraySet<Session> SESSION_SET = new CopyOnWriteArraySet<>();

    @PostConstruct
    public void init() {
        System.out.println("websocket 加载");
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        SESSION_SET.add(session);
        // 在线数加1
        int cnt = ONLINE_COUNT.incrementAndGet();
        log.info("有连接加入，当前连接数为：{}", cnt);
        sendMessage(session, "连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SESSION_SET.remove(session);
        int cnt = ONLINE_COUNT.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息：{}", message);
        sendMessage(session, "收到消息，消息内容：" + message);

    }

    /**
     * 出现错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @param message 消息
     */
    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)", message, session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 指定Session发送消息
     *
     * @param sessionId 指定Session
     * @param message   消息
     */
    public static void sendMessage(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : SESSION_SET) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            sendMessage(session, message);
        } else {
            log.warn("没有找到你指定ID的会话：{}", sessionId);
        }
    }

    /**
     * 群发消息
     *
     * @param message 消息
     */
    public static void broadCastInfo(String message) throws IOException {
        for (Session session : SESSION_SET) {
            if (session.isOpen()) {
                sendMessage(session, message);
            }
        }
    }
}
