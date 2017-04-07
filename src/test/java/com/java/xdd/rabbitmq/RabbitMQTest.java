package com.java.xdd.rabbitmq;

import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RabbitMQTest {

    private RabbitTemplate rabbitTemplate;

    @Before
    public void before(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        context.start();
        rabbitTemplate = context.getBean(RabbitTemplate.class);
    }

    @Test
    public void test1() throws Exception{
        this.rabbitTemplate.convertAndSend("不知道发送成功没哟u！");
        Thread.sleep(2000);// 休眠1秒
        System.out.println("发送成功！");
    }
}
