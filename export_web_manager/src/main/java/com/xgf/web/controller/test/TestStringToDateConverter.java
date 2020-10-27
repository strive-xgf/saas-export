package com.xgf.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
@Controller
public class TestStringToDateConverter {

    @RequestMapping(path="/testDate.do",method = RequestMethod.GET)
    //该参数接收浏览器提交的日期字符
    public String testDate(Date date, Model model){
        System.out.println("testDate date="+date);
        model.addAttribute("date",date);
        return "stringToDateConverter";
    }
}
