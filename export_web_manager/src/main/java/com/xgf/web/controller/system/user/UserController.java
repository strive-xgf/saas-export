package com.xgf.web.controller.system.user;

import com.github.pagehelper.PageInfo;

import com.xgf.domain.msg.Msg;
import com.xgf.domain.system.dept.Dept;
import com.xgf.domain.system.user.User;
import com.xgf.service.system.dept.IDeptService;
import com.xgf.service.system.user.IUserService;
import com.xgf.web.controller.system.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
    private static  final Logger l = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService iUserService;
    @Autowired
    IDeptService iDeptService;

    @RequestMapping(path="/toList",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toList(@RequestParam(defaultValue = "1") int curr, @RequestParam(defaultValue = "10")int pageSize){
        //调查询分页列表的方法
        PageInfo<User> pi = iUserService.findByPage(curr, pageSize,getLoginCompanyId());
        //将pi分页信息发送到jsp页面
        request.setAttribute("pi",pi);
        return "system/user/user-list";
    }


    //  删除用户
    @RequestMapping(path="/delete",method ={ RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object delete(String userId){//参数接收页面js提交过来的userId
        boolean flag = iUserService.deleteUser(userId);
        if(flag){
            return Msg.init(200,"删除成功",null);
        }else{
            return Msg.init(-200,"删除失败",null);
        }
    }

    //增加页面
    @RequestMapping(path="/toAdd",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toAdd(){
        //页面上有一个下拉菜单 ，需要查询所有的部门
        List<Dept> depts = iDeptService.findAll(getLoginCompanyId());
        //添加到request
        request.setAttribute("depts",depts);
        return "system/user/user-add";
    }

    //添加用户
    @RequestMapping(path="/add",method ={ RequestMethod.GET, RequestMethod.POST})
    public String add(User user){//接收页面提交过来的表单数据
        l.info("add user="+user);

        //用户或者员工属于一家公司，所以公司companyId是必须的
        user.setCompanyId(getLoginCompanyId());
        user.setCompanyName(getLoginCompanyName());

        l.info("add user="+user);

        iUserService.saveUser(user);

        return "redirect:/system/user/toList";
    }

    //更新回显
    @RequestMapping(path="/toUpdate",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toUpdate(String userId){//需要使用参数接收提交的 userId
        l.info("toUpdate userId="+userId);
        //需要根据userId查询当前模块的记录，回显

        User user =  iUserService.findUserById(userId);
        l.info("toUpdate user="+user);
        request.setAttribute("user",user);
        //页面上有一个下拉菜单 ，需要查询所有的部门
        List<Dept> depts = iDeptService.findAll(getLoginCompanyId());
        //添加到request
        request.setAttribute("depts",depts);
        return "system/user/user-update";
    }

    //更新用户
    @RequestMapping(path = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(User user) {//需要接收编辑页面提交的表单数据
        l.info("update user=" + user);
        //更新
        iUserService.updateUser(user);
        return "redirect:/system/user/toList";
    }


}
