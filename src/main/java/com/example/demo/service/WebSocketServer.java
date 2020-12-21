package com.example.demo.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {

    private static final Log Log = LogFactory.getLog(WebSocketServer.class);
    public Session session;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        Log.info("【websocket消息】有新的连接, 总数:{}" + webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        Log.info("【websocket消息】连接断开, 总数:{}" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        Log.info("【websocket消息】收到客户端发来的消息:{}" + message);
    }

    public static void sendMessage(String message) {
        System.out.println(message);
        for (WebSocketServer webSocket : webSocketSet) {
            Log.info("【websocket消息】广播消息, message={}" + message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
