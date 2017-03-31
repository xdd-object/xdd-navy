package com.java.xdd.websocket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.xdd.websocket.handler.WebSocketPushHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.util.*;

@Controller
public class MySocketTest {
    /**
     * 注入发送消息类实例
     * @return
     */
    @Bean
    public WebSocketPushHandler getWebSocketPushHandler() {
        return new WebSocketPushHandler();
    }

    /**
     * 根据用户获取消息内容
     */
    @RequestMapping("mySocketTest2")
    @ResponseBody
    public TextMessage getMsgByUserId(String userId){
        //Map<String, Object> resultMap = tcService.selectTask(userId);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("id",1);
        resultMap.put("name","小明");
        resultMap.put("age",20);


        ObjectMapper mapper = new ObjectMapper();
        String msg = "";
        try {
            msg = mapper.writeValueAsString(resultMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new TextMessage(msg);
    }

    @RequestMapping("mySocketTest")
    @ResponseBody
    public void aa(){
        TextMessage msgByUserId = this.getMsgByUserId("1");
        getWebSocketPushHandler().sendMessageToUser("1", msgByUserId);
    }

    @RequestMapping("mySocketTest3")
    public String bb(){
        return "socketTest";
    }
}
