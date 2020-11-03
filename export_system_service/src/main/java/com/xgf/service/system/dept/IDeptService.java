package com.xgf.service.system.dept;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.system.dept.Dept;


import java.util.List;

//部门业务接口
public interface IDeptService {
    //分页查询显示指定公司id的部门  参1：当前页  参2：每页数据大小  参3：公司id
    PageInfo<Dept> findByPage(int curr, int pageSize, String companyId);
    //根据公司id，companyId查找该公司下面的所有的部门
    List<Dept> findAll(String companyId);
    //通过部门id查找部门
    Dept findById(String deptId);
    //新建一个部门
    void saveDept(Dept dept);
    //更新部门 - 保存编辑页面的部门数据
    void updateDept(Dept dept);

    //查找以当前部门作为直接上级部门的部门数量
    int findParentCount(String deptId);
    //根据指定的deptId删除部门数据
    // 当前部门是不是其他部门的上级上级部门（两种情况）
    //1. 删除部门不是其他部门的上级部门，可以直接删除  2. 删除部门是其他部门的上级部门删除报错
    boolean deleteDeptById(String deptId);

}
