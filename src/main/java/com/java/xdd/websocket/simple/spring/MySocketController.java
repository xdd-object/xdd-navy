package com.java.xdd.websocket.simple.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MySocketController {
    /**
     * 注入发送消息类实例
     * @return
     */
    @Bean
    public WebsocketEndPoint getWebSocketPushHandler() {
        return new WebsocketEndPoint();
    }


    @RequestMapping("mySocketTest")
    @ResponseBody
    public String aa(){
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


        TextMessage msgByUserId = new TextMessage(msg);
        getWebSocketPushHandler().sendMessagesToUsers(msgByUserId);


        return msgByUserId.toString();
    }

}
