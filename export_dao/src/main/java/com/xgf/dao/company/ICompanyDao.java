package com.xgf.dao.company;


import com.xgf.domain.company.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyDao {
    //查询所有的公司记录
    //select * from ss_company
    List<Company> findAll();
}
