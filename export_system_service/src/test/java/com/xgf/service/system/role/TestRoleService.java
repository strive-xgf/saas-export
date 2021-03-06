package com.xgf.service.system.role;

import com.github.pagehelper.PageInfo;
import java.util.List;

import com.xgf.domain.system.role.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestRoleService {
    private static final Logger l = LoggerFactory.getLogger(TestRoleService.class);

    @Autowired
    IRoleService iRoleService;

    //测试角色分页显示
    @Test
    public void test01(){
        //分页列表
        //页面上显示分页列表，就要求业务方法中提供查询PageInfo的方法
       PageInfo<Role>  pi= iRoleService.findByPage(1,3,"1");
       l.info("分页角色 pi = "+pi);
    }

    //测试添加角色
    @Test
    public void test02(){
        //将一个表单数据保存在javaBean中，再将javaBean存到数据库
        Role role = new Role();
        role.setName("save添加角色");
        role.setRemark("save添加角色 - 备注");
        role.setCompanyId("1");
        role.setCompanyName("吉首大学");
        iRoleService.saveRole(role);
    }

    //测试更新角色
    @Test
    public void test03(){
        //更新业务  先根据id查找出对应的一条记录，编辑它的值，再将记录保存到数据库中
        String roleId="e7313d5b-993e-452a-a5ac-a2bdd935d0f0";
        //查找
        Role role =  iRoleService.findById(roleId);
        l.info("role="+role);
        //修改
        role.setName("更新角色name");
        role.setRemark("更新角色remark备注");
        //保存
       iRoleService.updateRole(role);
    }
    //测试通过id删除角色
    @Test
    public void test04(){
        //删除业务，就是根据指定的id，删除数据库中的记录
        String roleId="e7313d5b-993e-452a-a5ac-a2bdd935d0f0";
        //删除
        iRoleService.deleteRole(roleId);

    }

//多表
    //测试查询公司所有的角色、查询用户的角色
    @Test
    public void test05(){
        //user的角色列表
        String userId="e0de22fe-2c50-4216-ad75-ed0494d2dc92";
        String companyId="1";
        //所有的角色
        List<Role> allList =  iRoleService.findAll(companyId);
        //查找user自己的角色
        List<Role> userList =  iRoleService.findRolesByUserId(userId);
        l.info("test05 公司的所有角色的数量："+ allList.size() +"\tallList = "+allList);
        l.info("test05 用户的角色的数量： "+ userList.size() +"\tuserList = "+userList);
    }

    //测试给用户更新角色
    @Test
    public void test06(){
        //老王的角色列表
        String userId="e0de22fe-2c50-4216-ad75-ed0494d2dc92";
        String[] roleIds = {"4028a1cd4ee2d9d6014ee2df4c6a0001"};    //总经理角色
        iRoleService.updateUserRole(userId,roleIds);
    }

}
