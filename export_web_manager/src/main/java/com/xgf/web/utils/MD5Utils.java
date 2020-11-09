package com.xgf.web.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**MD5加密工具类
 * 进行数据加密
 * 当前项目没有用到这个工具类，仅做测试
 */
public class MD5Utils {
    //参1 传入的明文
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            //对明文的字节进行摘要加密
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有找到MD5算法");
        }

        //radix参数 16或者32（加密之后的位数）
        String md5code = new BigInteger(1, secretBytes).toString(16);

        //补0操作，一个字节转两位的16进制
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}

