package com.xgf.service.company.impl;


import com.xgf.dao.company.ICompanyDao;
import com.xgf.domain.company.Company;
import com.xgf.service.company.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //注解 @Service，该类交由Spring管理创建
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    ICompanyDao companyDao;
    public List<Company> findAll() {
        //service要调用dao查询数据，所以要注入
        return companyDao.findAll();
    }
}
