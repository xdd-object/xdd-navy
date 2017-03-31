package com.java.xdd.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Component
public class AESUtil {

    /*
     * 加密用的Key 可以用16个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    //@Value("${KEY}")
    private String sKey = "$%^$xiededuo$^%$";
    //@Value("${IV_PARAMETER}")
    private String ivParameter = "$+-*/!@##@!+-*/$";

    private static AESUtil instance;

    private AESUtil() {}

    public static AESUtil getInstance(){
        return instance == null ? new AESUtil() : instance;
    }

    /**
     * 加密
     * @param sSrc 加密数据
     * @return
     * @throws Exception
     */
    public String encrypt(final String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //算法/模式/补码方式
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

    /**
     * 解密
     * @param sSrc 解密数据
     * @return
     */
    public String decrypt(final String sSrc) throws Exception{
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        return originalString;
    }

    /**
     *
     * @param encData 加密数据
     * @param secretKey 需要的key
     * @param vector 需要的iv向量
     * @return
     * @throws Exception
     */
    public String encrypt(String encData, String secretKey, String vector) throws Exception {
        if (secretKey == null || secretKey.length() != 16) return null;

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = secretKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(vector.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(encData.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

    /**
     * 解密
     * @param sSrc 解密数据
     * @param key 解密的key
     * @param ivs 解密iv向量
     * @return
     * @throws Exception
     */
    public String decrypt(String sSrc, String key, String ivs) throws Exception{
        byte[] raw = key.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivs.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
        byte[] original = cipher.doFinal(encrypted1);
        String originalString = new String(original, "utf-8");
        return originalString;
    }
}