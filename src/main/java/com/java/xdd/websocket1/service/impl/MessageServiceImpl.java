package com.java.xdd.websocket1.service.impl;

import com.java.xdd.common.service.impl.BaseServiceImpl;
import com.java.xdd.websocket1.mapper.MessageMapper;
import com.java.xdd.websocket1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl implements MessageService{
    @Autowired
    private MessageMapper messageMapper;

    public void test(){
    }
}
