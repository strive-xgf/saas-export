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

    /*@Test
    public void test05(){

        List<Module> list = iModuleService.findAllModules();
        l.info("pi = "+list);
    }

    //角色B的权限，正确的是三个
    @Test
    public void test06(){

        List<Module> myList = iModuleService.findModuleByRoleId("4028a1cd4ee2d9d6014ee2df4c6a0010");
        l.info("myList = "+myList);
    }
    //角色B的权限，更新权限
    @Test
    public void test07(){
        //修改一个角色的权限，不仅仅是添加，也可能是减少
        String roleId="4028a1cd4ee2d9d6014ee2df4c6a0010";
        //String moduleIds="201,202";//减少指定角色的权限
        String moduleIds="201,202,203";//添加角色的权限
        iModuleService.updateRoleModule(roleId,moduleIds);
    }*/
}
