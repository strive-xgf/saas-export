package com.xgf.service.company;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.company.Company;

import java.util.List;

public interface ICompanyService {
    public List<Company> findAll();

    void saveCompany(Company company);

    void deleteById(String id);

    Company findById(String id);

    void updateCompany(Company company);

    //分页查询
    PageInfo<Company> findPage(int currentPage, int pageSize);

}
