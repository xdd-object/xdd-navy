package com.java.xdd.websocket.simple.spring;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//websocket的核心
public class WebsocketEndPoint extends TextWebSocketHandler {

    private static final List<WebSocketSession> users = new ArrayList<>();


    @Override
    protected void handleTextMessage(WebSocketSession session,  
            TextMessage message) throws Exception {  
        super.handleTextMessage(session, message);  
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");  
        session.sendMessage(returnMessage);
        users.add(session);
    }

    /**
     * 给所有的用户发送消息
     */
    public void sendMessagesToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                //isOpen()在线就发送
                if (user.isOpen()) user.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}