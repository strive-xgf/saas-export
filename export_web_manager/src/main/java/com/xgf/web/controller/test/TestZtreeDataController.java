package com.xgf.web.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/testZTree")
public class TestZtreeDataController{

    //以json格式向zTree树形菜单放数据
    @RequestMapping(path="/getZtreeData",method ={ RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getZtreeData(){

        /*
        页面数据
        var zNodes =[
            { id:1, pId:0, name:"Sass管理", open:true},
            { id:2, pId:1, name:"企业管理", open:true,checked:true},
            { id:3, pId:1, name:"模块管理"},
            { id:4, pId:1, name:"用户管理"},
            { id:5, pId:1, name:"角色管理"}
        ];
         */

        List<Map<String,Object>> list=new ArrayList<>();
        Map<String,Object> node1=new HashMap<String,Object>();
        node1.put("id",1);
        node1.put("pId",0); //菜单等级
        node1.put("name","Sass管理");
        node1.put("open",true); //是否展开该菜单

        Map<String,Object> node2=new HashMap<String,Object>();
        node2.put("id",11);
        node2.put("pId",1);
        node2.put("name","企业管理");
        node2.put("open",true);
        node2.put("checked",true);  //设置选中状态

        Map<String,Object> node3=new HashMap<String,Object>();
        node3.put("id",111);
        node3.put("pId",1);
        node3.put("name","模块管理");

        Map<String,Object> node4=new HashMap<String,Object>();
        node4.put("id",112);
        node4.put("pId",1);
        node4.put("name","用户管理");

        Map<String,Object> node5=new HashMap<String,Object>();
        node5.put("id",113);
        node5.put("pId",1);
        node5.put("name","角色管理");

        //因为五个元素放在[]中，所以本质上放到集合中的
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        list.add(node5);
        return list; //list虽然是对象，但会被 @ResponseBody转成json
    }
}
