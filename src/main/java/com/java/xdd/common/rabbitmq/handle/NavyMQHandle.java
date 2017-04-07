package com.java.xdd.common.rabbitmq.handle;

public class NavyMQHandle {

    /**
     * rabbitMQ 消费方法
     * @param msg
     */
    public void execute(String msg){
        System.out.println("执行成功！");
        System.out.println(msg);
    }
    public void execute2(String msg){
        System.out.println("执行成功2！");
        System.out.println(msg);
    }
}
