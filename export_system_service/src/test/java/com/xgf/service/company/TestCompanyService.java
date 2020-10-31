package com.xgf.service.company;


import com.github.pagehelper.PageInfo;
import com.xgf.domain.company.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

//Spring的单元测试
//@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
//  加载当前maven工程及其依赖工程的resources目录下的配置文件，读取所有符合规则的文件（tx和dao）
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestCompanyService {

    private static final Logger l = LoggerFactory.getLogger(TestCompanyService.class);
    @Autowired
    ICompanyService companyService;

    //测试查询所有
    @Test
    public void test01(){
        //1. 编写了业务逻辑的测试
        //ICompanyService  companyService = new CompanyServiceImpl();
        List<Company> list = companyService.findAll();
        System.out.println(list);
    }

    //测试save保存添加
    @Test
    public void test02(){
        //1. 将表单数据通过companyService正确写入到数据库（全参构造器）
        // public Company(String id, String name, Date expirationDate, String address, String licenseId, String representative, String phone, String companySize, String industry, String remarks, Integer state, Double balance, String city)
        Company company = new Company(null,"test02",new Date(),"address1","licenseId","representative","phone","companySize","industry","remarks",0,100.0,"city");
        //保存
        companyService.saveCompany(company);
    }

    //测试按照UUID生成的id进行删除
    @Test
    public void test03(){
        //删除
        String id = "fb8e866c-23af-4730-a833-3e0e7913ab3f";
        companyService.deleteById(id);
    }

    //通过id查找数据
    @Test
    public void test04(){
        //
        String id = "7f2a7416-2ffc-4f8d-bb90-3e79f2b87170"; //数据库中有的UUID生成的id
        Company company = companyService.findById(id);
        l.info(company+" ");
    }

    //通过id修改更新数据
    @Test
    public void test05(){
        //
        String id = "7f2a7416-2ffc-4f8d-bb90-3e79f2b87170";

        //查询
        Company company = companyService.findById(id);
        l.info(company+"7f2a7416-2ffc-4f8d-bb90-3e79f2b87170");
        //模拟修改数据
        if(company != null){
            company.setCity("北京");
            company.setName("test05修改name");
            l.info(company+" ");
            //将修改后的数据保存到数据库
            companyService.updateCompany(company);
        }else {
            l.info("没有查询到指定id的company");
        }


    }

    //测试分页
    @Test
    public void test06(){
        //当前页
        int currentPage = 1;
        //每页数据大小
        int pageSize = 2;
        //PageInfo 包含四个整数 一个集合
        PageInfo<Company> pi = companyService.findPage(currentPage,pageSize);

        l.info("test06 pi = "+pi);
    }


}
