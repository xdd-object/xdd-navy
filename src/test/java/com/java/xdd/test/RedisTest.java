package com.java.xdd.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;
import java.util.Set;

public class RedisTest {
    private ShardedJedis shardedJedis;
    //private RedisService redisService;

    @Before
    public void before(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        context.start();
        ShardedJedisPool shardedJedisPool = (ShardedJedisPool)context.getBean("shardedJedisPool");
        //redisService = (RedisService)context.getBean("redisService");
        shardedJedis = shardedJedisPool.getResource();
    }


    @Test
    public void test1(){
        String key = "ORDER_INFO_DATA_1";
        String value = shardedJedis.get(key);
        System.out.println(value);
    }

    @Test
    public void test2(){
        String key = "myset";
        Set<String> values = shardedJedis.smembers(key);
        for (String str : values){
            System.out.println(str);
        }
    }
    @Test
    public void test3(){
        String key = "list";
        String[] strs = {"abc1","abc2","abc3"};
        Long lpush = shardedJedis.lpush(key, strs);
        System.out.println(lpush);
    }
    @Test
    public void test4(){
        List<String> list = shardedJedis.lrange("list", 0, 1);
        System.out.println(list);
    }
    @Test
    public void test5(){
        String key = "list2";
        String[] strs = {"abc1","abc2","abc3"};
        //Long lpush = redisService.lpush(key, strs);
        //System.out.println(lpush);
    }







}
