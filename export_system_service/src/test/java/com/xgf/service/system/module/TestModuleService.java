package com.xgf.service.system.module;

import com.github.pagehelper.PageInfo;


import com.xgf.domain.system.module.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestModuleService {
    //日志记录
    private static final Logger l = LoggerFactory.getLogger(TestModuleService.class);

    @Autowired
    IModuleService iModuleService;
    //测试分页查询模块信息
    @Test
    public void test01(){
        PageInfo<Module>  pi= iModuleService.findByPage(1,3);
        l.info("分页模块：pi = "+pi);
    }
    //测试save添加一个模块
    @Test
    public void test02(){

        Module module = new Module();
        module.setName("添加模块");
        iModuleService.saveModule(module);

    }

    //测试更新模块
    @Test
    public void test03(){
        //更新业务  先根据id查找出对应的一条记录，编辑它的值，再将记录保存到数据库中
        String moduleId="8bec8054-78c5-46f0-a86a-413c1f6c8562";
        Module module =  iModuleService.findModuleById(moduleId);
        l.info("获取更新模块的信息：module="+module);
        //修改模块信息
        module.setName("更新模块");
        //执行更新
        iModuleService.updateModule(module);

    }

    //测试通过id删除模块
    @Test
    public void test04(){
        //删除业务，就是根据指定的id，删除数据库中的记录
        String moduleId="8bec8054-78c5-46f0-a86a-413c1f6c8562";
        //删除
        boolean flag = iModuleService.deleteModule(moduleId);
        l.info("删除模块结果："+flag);
    }


    //测试查找所有的模块
    @Test
    public void test05(){

        List<Module> moduleList = iModuleService.findAllModules();
        l.info("模块数量"+ moduleList.size() +"moduleList = "+moduleList);
    }

    //测试通过角色id查询角色的权限
    @Test
    public void test06(){

        List<Module> myList = iModuleService.findModuleByRoleId("ac9b2814-51bc-40db-8628-6cb20b77e5e9");
        l.info("模块数量： "+myList.size()+"  \n角色的模块权限myList = "+myList);
    }

    //测试更新角色的权限（先删除、后更新）
    @Test
    public void test07(){
        //修改一个角色的权限，不仅仅是添加，也可能是减少
        String roleId="ac9b2814-51bc-40db-8628-6cb20b77e5e9";
        //String moduleIds="201,202";//减少指定角色的权限
        String moduleIds="201,202,203";//添加角色的权限
        iModuleService.updateRoleModule(roleId,moduleIds);
    }

   /* @Test
    public void test08(){

        User user = new User();
        user.setUserId("0f1f71fe-fe7c-4a44-a952-4f08bf5aa990");
        //user.setDegree(0);
        //user.setDegree(1);//企业管理员
        user.setDegree(4);//普通用户
        //一个 Module对象 就是左侧栏上的一个菜单项
        List<Module> menus = iModuleService.findModulesByUser(user);
        l.info("test08 menus="+menus);

    }*/
}
