package com.xgf.web.controller;

import com.xgf.web.controller.company.CompanyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
public class TestException {
    private static final Logger l = LoggerFactory.getLogger(CompanyController.class);

    //测试统一异常处理类
    @RequestMapping(path="/testException.do",method = RequestMethod.GET)
    public String testDate(Date date){
        System.out.println();
        l.info("testDate date="+date);
        int num = 1/0;
        return "result";
    }
}

