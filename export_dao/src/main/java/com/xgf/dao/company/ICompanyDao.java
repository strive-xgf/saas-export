package com.xgf.dao.company;


import com.xgf.domain.company.Company;

import java.util.List;

public interface ICompanyDao {
    //查询所有的公司记录
    //select * from ss_company
    List<Company> findAll();
}
