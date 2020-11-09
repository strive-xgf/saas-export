package com.xgf.web.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

/**
 * 测试MD5加密工具类
 * 测试shiro自带的hash.Md5Hash加密
 */
public class MD5UtilsTest {

    //测试MD5工具类加密
    @Test
    public void stringToMD5() {
        //对123456进行MD5加密
        String result = MD5Utils.stringToMD5("123456");
        //md5（123456）= e10adc3949ba59abbe56e057f20f883e
        System.out.println(result);
    }

    //测试shiro自带的hash.Md5Hash算法加密，（shiro也集成常用的加密的算法md5,sha-1）
    @Test
    public void test02() {
        // 对123456加密
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println(md5Hash.toString()); //e10adc3949ba59abbe56e057f20f883e
    }

    //测试加盐加密（添加一个特殊字符串）
    @Test
    public void test03() {
        //参1：传入明文  参2：加盐（特殊字符串）
        Md5Hash md5Hash = new Md5Hash("123456","strive_day@163.com");
        System.out.println(md5Hash.toString());//2f660a3a69e023b9033c83ef3d418c9b
    }

    //测试加另一个盐（另一个字符串）
    @Test
    public void test04() {
        Md5Hash md5Hash = new Md5Hash("123456","study@export.com");//参1 传入明文  参2盐
        System.out.println(md5Hash.toString());//4154661a85ae8bafac91433e7003f4a2和上面的密文不一样，因为特殊字符串盐不一样
    }
}