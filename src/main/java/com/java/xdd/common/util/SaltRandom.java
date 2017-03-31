package com.java.xdd.common.util;

import java.util.Random;

/**
 * 随机获取盐
 */
public class SaltRandom {

    private static String[] salts;

    private SaltRandom(){}

    static {
        salts = new String[]{"A","B","C","D","E","F","G","H","I","J","K",
                             "L","M","N","O","P","Q","R","S","T","U","V",
                             "W","X","Y","Z","1","2","3","4","5","6","7",
                             "8","9"};
    }

    /**
     * 随机获取5位的盐
     * @return
     */
    public static String getSalt(){
        String salt = "";
        Random random = new Random();
        for (int i = 0 ; i < 5 ; i++){
            salt = salt + salts[random.nextInt(salts.length)];
        }
        return salt;
    }
}
