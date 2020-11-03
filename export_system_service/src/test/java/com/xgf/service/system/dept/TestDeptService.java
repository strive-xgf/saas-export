package com.xgf.service.system.dept;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.system.dept.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestDeptService {
    private static final Logger l = LoggerFactory.getLogger(TestDeptService.class);

    @Autowired
    IDeptService deptService;

    //测试查询公司（通过登录公司的id）的所有部门信息
    @Test
    public void test01(){
        //给页面的下拉菜单按公司查找所有的部门
        String  companyId="1";

        List<Dept> list = deptService.findAll(companyId);
        //打印
        l.info("test02 部门列表list="+list);
    }

    //测试分页查询显示（每页3条）指定公司id的部门  参1：当前页  参2：每页数据大小  参3：公司id
    @Test
    public void test02(){
        //部门分页显示
        int curr=1;
        int pageSize=3;
        String  companyId="1";
        //调用分页查找方法  参1：当前第几页  参2：每页多少条 参3：公司id
        PageInfo<Dept> pi = deptService.findByPage(curr,pageSize,companyId);
        //日志打印
        l.info("test01 分页信息pi="+pi);
    }


    //测试增加一个部门
    @Test
    public void test03(){
        //模拟表单
        Dept dept = new Dept();
        dept.setCompanyId("1");
        dept.setDeptName("最牛13的java部门");
        dept.setState(1);

        Dept parent = new Dept();
        //设置上级部们id
        parent.setDeptId("100101101");

        dept.setParent(parent);
        //保存到数据库
        deptService.saveDept(dept);
    }

    //测试通过id查询部门信息，进行页面回显（更新页面）
    @Test
    public void test04(){

        String deptId="100101";
        Dept dept = deptService.findById(deptId);

        l.info("test04  dept="+dept);
    }

    //测试查询以当前部门id为直接上级部门的部门数量（不能直接删除的判断）
    @Test
    public void test05(){
        //测试删除id=100的部门，该部门有给其他部门作上级部门（应该删除失败）
        String deptId="100";
        //根据id删除（service会判断该部门是否有下级部门）
        int count=deptService.findParentCount(deptId);
        l.info("test05 查询到deptid="+deptId+"作为直接上级的部门有 " + count + "个");
    }


    //通过id删除部门
    @Test
    public void test06(){
        //测试删除id=100的部门，该部门有给其他部门作上级部门（应该删除失败）
        String deptId="100";
        //根据id删除（service会判断该部门是否有下级部门）
        boolean result=deptService.deleteDeptById(deptId);
        l.info("test06 删除结果result="+result);
    }

    //进行更新操作
    @Test
    public void test07(){

        String deptId="0c9616fb-7741-4b21-be7a-9f4c2fbbc6af";
        Dept dept = deptService.findById(deptId);
        //1 模拟页面的修改
        dept.setDeptName("update dept更新部门名称");

        //所属公司
        dept.setCompanyName("吉首大学");
        dept.setCompanyId("1");

        //上级部门设为""空值或者null，也就是无上级部门，自己就是顶级部门
        Dept parent = new Dept();//下拉菜单
        parent.setDeptId("");//也可以赋值上 ""
        dept.setParent(parent);
        //状态设置为停用
        dept.setState(0);

        //2 保存到数据库
        deptService.updateDept(dept);

        l.info("test07  更新dept="+dept);
    }

}
