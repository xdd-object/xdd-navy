package com.java.xdd.test;

import com.java.xdd.common.httpclient.HttpClientUtil;
import com.java.xdd.common.httpclient.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.ShardedJedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huanghu on 2017/4/8.
 */
public class HttpTest {
    private HttpClientUtil httpClient;

    //@Before
    public void before(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        context.start();
        ShardedJedisPool shardedJedisPool = (ShardedJedisPool)context.getBean("shardedJedisPool");
        httpClient = (HttpClientUtil) context.getBean("httpClientUtil");
    }

    @Test
    public void test(){
        try {
            String host = "https://dm-81.data.aliyun.com";
            String path = "/rest/160601/ip/getIpInfo.json";
            String method = "";
            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "APPCODE 1615401819d3477cbdaa21107e749111");
            Map<String, String> querys = new HashMap<>();
            querys.put("ip", "116.25.45.16");

            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);

            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        try {
            String host = "https://api.map.baidu.com";
            String path = "/location/ip";
            String method = "";
            Map<String, String> headers = new HashMap<>();
            //headers.put("Authorization", "APPCODE 1615401819d3477cbdaa21107e749111");
            Map<String, String> querys = new HashMap<>();
            querys.put("ip", "116.25.45.16");
            querys.put("ak", "zDyZneP3vBERNestqdHYzsWQ60KfLDy0");

            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            HttpEntity entity = response.getEntity();
            String s = EntityUtils.toString(entity);

            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
