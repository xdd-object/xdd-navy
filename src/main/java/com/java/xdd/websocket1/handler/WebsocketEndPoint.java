package com.java.xdd.websocket1.handler;

import com.alibaba.fastjson.JSONObject;
import com.java.xdd.common.domain.BaseUser;
import com.java.xdd.common.util.PrincipalUtil;
import com.java.xdd.shiro.domain.User;
import org.apache.commons.lang3.StringUtils;
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
    private static final Map<String, Map<Long, WebSocketSession>> users = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        Map<String, Object> attributes = session.getAttributes();
        System.out.println(attributes);

        String payload = message.getPayload(); //接收到的消息

        User user = this.getSessionUser(session);
        logger.debug("用户【{}】发送的消息【{}】!!", user, payload);

        Map<String, Object> map = JSONObject.parseObject(payload, Map.class);
        Object sendTo = map.get("sendTo");
        map.put("sendTo", user.getId());
        map.put("sendToUserId", user.getUsername());
        if (null != sendTo && StringUtils.isNotBlank(sendTo.toString())) {
            Long sendUserId = Long.valueOf(sendTo.toString());
            Map<Long, WebSocketSession> sessions = users.get("0");
            WebSocketSession sendSeesion = sessions.get(sendUserId);
            if (null != sendSeesion && sendSeesion.isOpen()) { //用户在线
                TextMessage returnMessage = new TextMessage(JSONObject.toJSONString(map));
                sendSeesion.sendMessage(returnMessage);//发送消息
            }
        }


        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        //session.sendMessage(returnMessage); //发送消息

        //StandardWebSocketClient client = new StandardWebSocketClient();
    }

    //用户进入系统监听
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<Long, WebSocketSession> sessions = users.get("0"); //非群聊房间
        if (null == sessions) {
            sessions = new HashMap<>();
            users.put("0", sessions);
        }
        User user = this.getSessionUser(session);
        sessions.put(user.getId(), session);


        logger.info("用户【{}】成功进入了系统。。。", this.getSessionUser(session));
        System.out.println("成功进入了系统。。。");
    }

    //后台错误信息处理方法
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.error("后台出错了！报错原因【{}】，来自用户【{}】", exception.getMessage(), this.getSessionUser(session));
        System.out.println("后台出错了！");
    }

    //用户退出后的处理，不如退出之后，要将用户信息从websocket的session中remove掉，这样用户就处于离线状态了，也不会占用系统资源
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        User user = this.getSessionUser(session);
        logger.info("用户【{}】退出系统！！！退出原因【{}】", user, closeStatus);
        if (session.isOpen()) {
            users.get("0").remove(user.getId());
        }
        System.out.println("安全退出了系统");

    }

    private User getSessionUser(WebSocketSession session){
        if (null == session) return null;
        Principal principal = session.getPrincipal();
        return PrincipalUtil.getUserPrincipal(principal);
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