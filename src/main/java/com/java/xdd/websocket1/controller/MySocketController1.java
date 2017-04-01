package com.java.xdd.websocket1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.xdd.websocket1.handler.WebsocketEndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MySocketController1 {

    private WebsocketEndPoint websocketEndPoint;

    /**
     * 注入发送消息类实例
     * @return
     */
    public WebsocketEndPoint getWebsocketEndPoint() {
        return this.websocketEndPoint;
    }


    //使用http请求给websocket指定用户发送请求
    @RequestMapping("mySocketTest9")
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
        //getWebsocketEndPoint().sendMessagesToUsers(msgByUserId);

        return msgByUserId.toString();
    }

    @RequestMapping("websocket")
    public String websocket(){
        return "websocket";
    }
    @RequestMapping("main")
    public String main(){
        return "main";
    }

}
