package com.xgf.web.controller.company;


import com.github.pagehelper.PageInfo;
import com.xgf.domain.company.Company;
import com.xgf.service.company.ICompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//注解方式实现权限校验
@Controller
@RequestMapping("/company")
public class CompanyController {
    private static final Logger l = LoggerFactory.getLogger(CompanyController.class);
    @Autowired
    ICompanyService companyService;

    //去company的所有公司信息页面 分页显示
    @RequiresPermissions("企业管理")    //当前用户需要有【企业管理】权限才能访问包含的内容
    @RequestMapping(path="/toList",method = {RequestMethod.GET,RequestMethod.POST})
    public String toList( Integer curr, Integer pageSize, Model model){
        //调service获取数据
        if (curr == null) {
            curr = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageInfo<Company> pi = companyService.findPage(curr,pageSize);
        l.info("toList pi="+pi);
        model.addAttribute("pi",pi);
        //将数据发到页面，使用标签
        return "company/company-list";
    }

    //${path}/company/update
    @RequestMapping(path="/update",method = RequestMethod.POST)
    public String update(Company company){
        l.info("update company="+company);

        companyService.updateCompany(company);

        return "redirect:/company/toList";//重定向跳转到列表页面
    }

    //${path}/company/toEdit?id=${item.id}
    @RequiresPermissions("企业管理")    //当前用户需要有【企业管理】权限才能访问包含的内容
    @RequestMapping(path="/toEdit",method = RequestMethod.GET)
    public String toEdit(String id,Model model){
        l.info("toEdit id="+id);
        Company company=companyService.findById(id);
        l.info("toEdit company="+company);

        model.addAttribute("company",company);
        return "company/company-update";
    }


    //${path}/company/delete?id="+id
    @RequestMapping(path="/delete",method = RequestMethod.GET)
    public String delete(String id){
        l.info("delete id="+id);
        companyService.deleteById(id);
        return "redirect:/company/toList";//跳转到列表页面
    }

    //${path}/company/add  技巧1：表单的name值对应实体类的变量名
    @RequestMapping(path="/add",method = RequestMethod.POST)
    public String add(Company company){
        l.info("add company="+company);
        companyService.saveCompany(company);
        return "redirect:/company/toList";//跳转到列表页面
    }


    //添加 1 打开添加页面
    @RequestMapping(path="/toAdd",method = RequestMethod.GET)
    public String toAdd(){
        return "company/company-add";
    }

    //查询 1 打开列表页面
    @Deprecated
    @RequestMapping(path="/toList1",method = RequestMethod.GET)
    public String toList1(Model model){
        //调service获取数据
        List<Company> list = companyService.findAll();
        l.info("toList list="+list);
        model.addAttribute("list",list);
        //将数据发到页面，使用标签
        return "company/company-list";
    }

}
