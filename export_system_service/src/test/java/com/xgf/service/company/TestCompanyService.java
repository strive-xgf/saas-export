package com.xgf.service.company;

import com.xgf.domain.company.Company;
import com.xgf.service.company.impl.CompanyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//Spring的单元测试
//@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
//  加载当前maven工程及其依赖工程的resources目录下的配置文件，读取所有符合规则的文件（tx和dao）
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestCompanyService {
    @Autowired
    ICompanyService companyService;
    @Test
    public void test01(){
        //1:编写了业务逻辑的测试
        //ICompanyService  companyService = new CompanyServiceImpl();
        List<Company> list = companyService.findAll();
        System.out.println(list);
    }

}
