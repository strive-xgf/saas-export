package com.xgf.service.company.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xgf.dao.company.ICompanyDao;
import com.xgf.domain.company.Company;
import com.xgf.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service //注解 @Service，该类交由Spring管理创建
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    ICompanyDao companyDao;
    /*@Autowired
    ICompanyDao companyDao;*/
    public List<Company> findAll() {
        //service要调用dao查询数据，所以要注入
        return companyDao.findAll();
    }

    @Override
    public void saveCompany(Company company) {
        //当前数据库的id不是自增长的。
        //02e1da04-43f8-42e1-a4c2-66e162c6f4a5 生成uuid 全球唯一
        String id = UUID.randomUUID().toString();
        company.setId(id);
        companyDao.save(company);
    }

    @Override
    public void deleteById(String id) {
        companyDao.deleteById(id);
    }

    @Override
    public Company findById(String id) {
        Company company = companyDao.findById(id);
        return company;
    }

    @Override
    public void updateCompany(Company company) {
        //调用dao操作数据库
        companyDao.update(company);
    }

    @Override
    public PageInfo<Company> findPage(int currentPage, int pageSize) {
        //设置参数
        PageHelper.startPage(currentPage,pageSize);
        //查询由拦截器在 select * from ss_company 基础上，生成
        //select count(*) from ss_company
        //拦截器拦截，拼接语句（sql后面不能加;分号），并不是全查
        //select * from ss_company limit (currentPage-1)*pageSize ,pageSize
        List<Company> list = companyDao.findAll();
        //将集合封装
        PageInfo<Company> pi = new PageInfo<>(list);
        return pi;
    }

}
