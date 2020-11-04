package com.xgf.web.controller.system.module;

import com.github.pagehelper.PageInfo;

import com.xgf.domain.msg.Msg;
import com.xgf.domain.system.module.Module;
import com.xgf.service.system.module.IModuleService;
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
@RequestMapping("/system/module")
public class ModuleController extends BaseController {
    private static  final Logger l = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    IModuleService iModuleService;

    //分页显示模块信息
    @RequestMapping(path="/toList",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toList(@RequestParam(defaultValue = "1") int curr, @RequestParam(defaultValue = "10")int pageSize){
        //调查询分页列表的方法
        PageInfo<Module> pi = iModuleService.findByPage(curr, pageSize);
        //将pi添加到页面
        request.setAttribute("pi",pi);
        return "system/module/module-list";
    }

    //增加模块页面
    @RequestMapping(path="/toAdd",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toAdd(){
        //页面上有一个下拉菜单 ，需要查询所有的模块
        List<Module> list = iModuleService.findAllModules();
        //添加到request
        request.setAttribute("list",list);
        return "system/module/module-add";
    }

    //增加模块
    @RequestMapping(path="/add",method ={ RequestMethod.GET, RequestMethod.POST})
    public String add(Module module){//接收页面提交过来的表单数据（Module类数据接收）
        l.info("add module = "+module);

        iModuleService.saveModule(module);
        return "redirect:/system/module/toList";
    }

    //更新回显页面
    @RequestMapping(path="/toUpdate",method ={ RequestMethod.GET, RequestMethod.POST})
    public String toUpdate(String moduleId){//需要使用参数接收提交的 moduleId
        l.info("toUpdate moduleId="+moduleId);
        //需要根据moduleId查询当前模块的记录，回显
        Module module =  iModuleService.findModuleById(moduleId);
        l.info("toUpdate module="+module);
        request.setAttribute("module",module);

        //查找所有模块，给更改页面设置上级模块（下拉选择框，选择上级模块）
        List<Module> modules = iModuleService.findAllModules();
        request.setAttribute("modules",modules);
        return "system/module/module-update";
    }

    //更新模块信息
    @RequestMapping(path="/update",method ={ RequestMethod.GET, RequestMethod.POST})
    public String update(Module module){//需要接收编辑页面提交的表单数据
        l.info("update module="+module);
        //更新
        iModuleService.updateModule(module);
        return "redirect:/system/module/toList";
    }


    //  ajax删除模块信息  {code:200,msg:'删除成功',data:null}
    @RequestMapping(path="/delete",method ={ RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    Object delete(String moduleId){//参数接收页面js提交过来的moduleId

        boolean flag = iModuleService.deleteModule(moduleId);
        if(flag){
            return Msg.init(200,"moduleId = 【"+ moduleId +"】的模块删除成功！",null);
        }else{
            return Msg.init(-200,"moduleId = 【"+ moduleId +"】的模块删除失败，因为它有下级模块。",null);
        }
    }

}
