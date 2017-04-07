package com.java.xdd.rabbitmq;


import com.rabbitmq.client.*;
import org.junit.Before;
import org.junit.Test;

public class RabbitMQ {
    private Connection connection;

    private final String QUEUE_NAME = "xdd-navy";

    @Before
    public void getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("127.0.0.1");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("123456");
        // 通过工程获取连接
        connection = factory.newConnection();
        System.out.println(connection);
    }

    @Test
    public void sendMessage() throws Exception {
        // 1.获取到连接以及mq通道
        // 2.从连接中创建通道
        Channel channel = connection.createChannel();

        // 3.声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 4.消息内容
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        //5.关闭通道和连接
        channel.close();
        connection.close();
    }
    @Test
    public void getMessage() throws Exception {
        // 1.获取到连接以及mq通道
        Channel channel = connection.createChannel();

        // 2.声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 3.定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel);
        // 4.监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // 5.获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }

    }

}
