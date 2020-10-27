package com.xgf.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/home")
public class HomeController {
    //访问WEB-INF下的页面不能直接通过重定向url，只能通过请求转发啊
    //主要是负责打开页面的，没有逻辑 跳转到主页
    @RequestMapping(path="/toMain",method = RequestMethod.GET)
    public String toMain(){
        return "home/main";
    }

    @RequestMapping(path="/toMain22",method = RequestMethod.GET)
    public String toMain22(){
        return "home/main22";
    }
}
