package com.xgf.dao.company;

import com.xgf.dao.company.ICompanyDao;
import com.xgf.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//通过Spring自带的junit测试 - 两个注解
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-dao.xml")
public class TestCompanyDao {
    //通过@Autowrie从spring中获取dao的实现类对象
    @Autowired
    ICompanyDao companyDao;//自动装载，就相当于ICompanyDao iCompanyDao = new iCompanyDao()
    @Test
    public void test01(){
        //测试自动装载能否实现
        System.out.println("companyDao对象：" + companyDao);

        List<Company> companyList = companyDao.findAll();
        System.out.println(companyList);
    }
}
