package com.xgf.web.controller.system.role;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xgf.domain.msg.Msg;
import com.xgf.domain.system.module.Module;
import com.xgf.domain.system.role.Role;
import com.xgf.service.system.module.IModuleService;
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
    @Autowired
    IModuleService iModuleService;

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



    // 去角色的模块表（需要角色的id，查找该角色的模块） location.href="${path}/system/role/toRoleModule?roleId="+id;
    @RequestMapping(path="/toRoleModule",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toRoleModule(String roleId){//接收页面提交的roleId
        //当前授权页面需要显示 角色名称
        l.info("toRoleModule roleId=" + roleId);
        //查找角色信息
        Role role = iRoleService.findById(roleId);

        //数据转发到页面
        request.setAttribute("role",role);
        return "system/role/role-module";
    }

    // 给role-module页面的zTree的树形角色模块菜单赋值（有权限的选中）  $.get('${path}/role/getZtreeData?roleId=${role.roleId}',fn,'json')
    @RequestMapping(path="/getZtreeData",method ={ RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody Object getZtreeData(String roleId) {//接收页面提交的roleId
        //所有的权限（模块）查询出来
        List<Module> all = iModuleService.findAllModules();
        //根据roleId查询该角色的权限（有权限的模块打钩）
        List<Module> myList = iModuleService.findModuleByRoleId(roleId);

        //将list转换成 List<Map<String,Object>>  { id:1, pId:0, name:"Sass管理", open:true},
        List<Map<String,Object>> list = new ArrayList<>();
        //返回给页面
        for(Module m:all){
            //生成一个集合 Map<String,Object> 表示一节点
            Map<String,Object> node = new HashMap<String,Object>();
            node.put("id",m.getModuleId());
            node.put("pId",m.getParentId());    //菜单等级
            node.put("name",m.getName());
            node.put("open",true);              //是否展开该菜单
            //比较所有模块（权限）和当前角色拥有的模块是否相同
            if(isInMyList(m,myList)){
                node.put("checked",true);//当前角色拥有该权限，选中状态，有打勾就表示有这个权限，否则就是没有
            }
            //添加到集合中
            list.add(node);
        }
        return list;    //@ResponseBody将list转成json
    }

    //定义方法判断当前模块是否在该角色的模块列表用
    //需要判断m是否在myList里面，如果在表示该角色有这个权限，否则没有
    private boolean isInMyList(Module m, List<Module> myList) {
        for(Module my:myList){
            if(m.getModuleId().equals(my.getModuleId())){
                l.info("isInMyList  moduleId1  "+m.getModuleId()+" "+m.getName());
                l.info("isInMyList  moduleId2  "+my.getModuleId()+" "+my.getName());
                return true;
            }
        }
        return false;
    }

    //更新角色的模块（先全部删除，再根据选中的模块进行赋值到中间表角色模块表中）  ${path}/system/role/updateRoleModule
    @RequestMapping(path="/updateRoleModule",method ={ RequestMethod.GET, RequestMethod.POST})
    public String updateRoleModule(String roleId,String moduleIds){//接收了页面提交的参数
        l.info("updateRoleModule roleId="+roleId);
        l.info("updateRoleModule moduleIds="+moduleIds);
        //roleId=ac9b2814-51bc-40db-8628-6cb20b77e5e9
        //更新角色的模块权限  moduleIds=202,203,204
        iModuleService.updateRoleModule(roleId,moduleIds);

        return "redirect:/system/role/toList";
    }



}
