package com.xgf.service.system.user;

import com.github.pagehelper.PageInfo;

import com.xgf.domain.system.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestUserService {
    //记录日志
    private static final Logger l = LoggerFactory.getLogger(TestUserService.class);


    @Autowired
    IUserService iUserService;

    //测试通过公司id查询所有公司用户，分页显示
    @Test
    public void test01(){
        String companyId="1";
        //作为一个公司用户的管理员，只能查自己公司的员工。分页显示当前第一页，每页3条数据
        PageInfo<User>  pi= iUserService.findByPage(1,3,companyId);
        l.info("pi = "+pi);
    }

    //测试新增一个用户
    @Test
    public void test02(){
        User user = new User();
        user.setUserName("新增用户addUser");
        user.setDeptId(UUID.randomUUID().toString());
        user.setDeptId("1");
        user.setCompanyId("1");
        iUserService.saveUser(user);
        l.info("增加User  ---  : " + user);
    }

    //更新用户信息
    @Test
    public void test03(){
        String userId="899676f8-9de7-4ae0-93f3-61869dc9fedb";
        User user =  iUserService.findUserById(userId);
        l.info("test03 更新 user = "+user);
        //修改
        user.setUserName("update User 更新");
        //保存
        iUserService.updateUser(user);
    }

    //测试删除
    @Test
    public void test04(){
        //
        //删除业务，就是根据指定的id，删除数据库中的记录
        String userId="899676f8-9de7-4ae0-93f3-61869dc9fedb";
        //删除
        boolean flag = iUserService.deleteUser(userId);
        l.info(flag+"");

    }

    //通过公司查找所有的用户（员工）
    @Test
    public void test05(){
        //A公司管理员，只能查A公司用户或者员工
        String companyId="1";
        List<User> list = iUserService.findAllUsers(companyId);
        l.info("公司的所有userList = "+list);
    }
}