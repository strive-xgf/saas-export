package com.xgf.web.controller.system.role;

import com.github.pagehelper.PageInfo;

import com.xgf.domain.msg.Msg;
import com.xgf.domain.system.role.Role;
import com.xgf.service.system.role.IRoleService;
import com.xgf.web.controller.system.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
    //记录日志
    private static  final Logger l = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    IRoleService iRoleService;


    //角色分页显示
    @RequestMapping(path="/toList",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toList(@RequestParam(defaultValue = "1") int curr, @RequestParam(defaultValue = "10")int pageSize){
        //调查询分页列表的方法，如果没有传递分页参数默认第一页，每页10条数据
        PageInfo<Role> pi = iRoleService.findByPage(curr, pageSize, getLoginCompanyId());
        //将pi传给jsp页面
        request.setAttribute("pi",pi);
        return "system/role/role-list";
    }

    //去角色增加页面
    @RequestMapping(path="/toAdd",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toAdd(){
        return "system/role/role-add";
    }

    //添加角色
    @RequestMapping(path="/add",method ={ RequestMethod.GET, RequestMethod.POST})
    public String add(Role role){//接收页面提交过来的表单
        l.info("add role=" + role);
        role.setCompanyId(getLoginCompanyId());
        role.setCompanyName(getLoginCompanyName());
        iRoleService.saveRole(role);
        //添加成功
        return "redirect:/system/role/toList";
    }

    // 更新回显页面 ${path}/system/role/toUpdate?roleId=${o.roleId}
    @RequestMapping(path="/toUpdate",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toUpdate(String roleId){//需要使用参数接收提交的 roleId
        Role role =  iRoleService.findById(roleId);
        l.info("toUpdate role="+role);

        //回显到页面
        request.setAttribute("role",role);
        return "system/role/role-update";
    }

    //更新角色  action="${path}/system/role/update"
    @RequestMapping(path="/update",method ={ RequestMethod.GET, RequestMethod.POST})
    public String update(Role role){//需要接收编辑页面提交的表单数据
        l.info("update role="+role);
        //更新角色
        //重新设置companyId companyName（不能更改登录的公司信息 - 角色从属与公司，不同公司不同角色，不能更改为其他公司的）
        role.setCompanyId(getLoginCompanyId());
        role.setCompanyName(getLoginCompanyName());

        iRoleService.updateRole(role);
        return "redirect:/system/role/toList";
    }

    // 删除角色ajax异步提示  ${path}/system/role/delete?roleId='+id;  {code:200,msg:'删除成功',data:null}
    @RequestMapping(path="/delete",method ={ RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object delete(String roleId){//参数接收页面js提交过来的roleId
        try {
            iRoleService.deleteRole(roleId);
            //成功
            return Msg.init(200,"角色编号【roleId = " + roleId + "】的角色删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
            //失败
            return Msg.init(-200,"角色编号【roleId = " + roleId + "】的角色删除失败",null);
        }
    }

}
