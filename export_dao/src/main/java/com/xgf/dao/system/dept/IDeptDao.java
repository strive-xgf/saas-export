package com.xgf.dao.system.dept;


import com.xgf.domain.system.dept.Dept;

import java.util.List;


public interface IDeptDao {

    //通过公司id查找所有部门
    List<Dept> findAll(String companyId);
    //保存一个部门
    void save(Dept dept);
    //通过部门id查找部门信息（上级部们）
    Dept findById(String deptId);
    //更新部门信息
    void update(Dept dept);
    //统计当前部门作为其他部门的上级的数量，也就是当前部门的直接下级部门的数量（直接下级）
    //如果当前部门，没有直接下级部门，那么删除就能成功，否则删除失败（不能删除有下级部门的部门）
    int findParentCount(String deptId);
    //通过id删除部门
    void deleteById(String deptId);


}
