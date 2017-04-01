package com.java.xdd.websocket1.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//拦截器
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor{  

    //连接之前
    @Override  
    public boolean beforeHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
        System.out.println("握手之前");
        Principal principal = request.getPrincipal();

        return super.beforeHandshake(request, response, wsHandler, attributes);  
    }  

    //连接之后
    @Override  
    public void afterHandshake(ServerHttpRequest request,  
            ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {
        //Collections
        System.out.println("握手之后");
        super.afterHandshake(request, response, wsHandler, ex);  
    }
  
}  