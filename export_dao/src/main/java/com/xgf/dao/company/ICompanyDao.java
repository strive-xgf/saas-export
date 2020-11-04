package com.xgf.dao.company;


import com.xgf.domain.company.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICompanyDao{
    //查询所有的公司记录
    //select * from ss_company
    List<Company> findAll();

    //保存增加一个company
    void save(Company company);

    //通过id删除company
    void deleteById(String id);

    //通过id查询company
    Company findById(String id);

    //更新company
    void update(Company company);
}