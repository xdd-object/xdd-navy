package com.java.xdd.test;


import org.junit.Test;

public class StirngTest {
    @Test
    public void test1(){
        String name = "分割重建方案";
        String temp = name.substring(0,name.length() - 2 );
        System.out.println(temp);
    }
}
