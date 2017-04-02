package com.java.xdd.websocket1.handler;

import com.java.xdd.common.domain.BaseUser;
import com.java.xdd.shiro.domain.User;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.lang.reflect.Method;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//websocket的核心
public class WebsocketEndPoint extends TextWebSocketHandler {

    private Logger logger = LoggerFactory.getLogger(WebsocketEndPoint.class);


    //private static final List<WebSocketSession> users = new ArrayList<>();
    //StandardWebSocketClient

    //改造 {"房间号":{"用户id":"session", "用户id":"session"}}
    private static final Map<String, Map<String, WebSocketSession>> users = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        Set<Map.Entry<String, Object>> entries = attributes.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            System.out.println(entry.getKey());
        }
        Principal principal = session.getPrincipal();

        Class clz = principal.getClass();
        Method clz1 = clz.getDeclaredMethod("getObject");
        Object invoke = null;
        try {
            clz1.setAccessible(true);
            invoke = clz1.invoke(principal);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


        if (invoke instanceof User){
            System.out.println("---------------------");
            User user = (User) invoke;
            System.out.println(user.getId());
            System.out.println(user.getSalt());
            System.out.println(user.getPassword());
            System.out.println(user.getUsername());


        }

        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");  
        session.sendMessage(returnMessage);

        StandardWebSocketClient client = new StandardWebSocketClient();
    }

    //用户进入系统监听
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("用户【{}】成功进入了系统。。。", session);
        System.out.println("成功进入了系统。。。");
    }

    //后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("后台出错了！报错原因【{}】，来自用户【{}】", exception.getMessage(), session);
        System.out.println("后台出错了！");
    }

    //用户退出后的处理，不如退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("用户【{}】退出系统！！！退出原因【{}】", session, closeStatus);
        if (session.isOpen()) users.remove(session);
        System.out.println("安全退出了系统");

    }
















    /**
     * 给所有的用户发送消息
     */
    /* public void sendMessagesToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                //isOpen()在线就发送
                if (user.isOpen()) user.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //用户进入系统监听
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("成功进入了系统。。。");
        users.add(session);
    }

    //后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("后台出错了！");
    }

    //用户退出后的处理，不如退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        if (session.isOpen()) users.remove(session);
        System.out.println("安全退出了系统");

    }*/


}