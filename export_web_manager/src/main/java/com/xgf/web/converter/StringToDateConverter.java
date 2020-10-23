package com.xgf.web.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*自定义类型参数转换器: 将字符串日期格式转成Date类型的数据.
     Converter<S,T>  S: 代表的是源类型,将要转换的数据类型  T:目标类型,将会转成什么数据类型
*/
//转换器，String转Date数据类型 日期格式转换器
public class StringToDateConverter implements Converter<String, Date> {
    //转换方法
    //String source 是传递过来的日期的字符串数据
    @Override
    public Date convert(String source) {
        //参数填写为你想要转换的数据格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
