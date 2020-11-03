package com.xgf.service.system.dept.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xgf.dao.system.dept.IDeptDao;
import com.xgf.domain.system.dept.Dept;
import com.xgf.service.system.dept.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//部门业务实现类
@Service
public class DeptServiceImpl implements IDeptService {

    //还需要注入dao 根据三层架构  service调用dao
    @Autowired
    IDeptDao deptDao;
    @Override
    public PageInfo<Dept> findByPage(int curr, int pageSize, String companyId) {
        //调用dao查询所有的记录，并分页显示
        //select * from dept;
        //select * from dept limit curr-1,pageSize;    //拦截器增加分页查询
        PageHelper.startPage(curr,pageSize);
        List<Dept> list =  deptDao.findAll(companyId);
        return new PageInfo<>(list);
    }

    @Override
    public List<Dept> findAll(String companyId) {
        List<Dept> list =  deptDao.findAll(companyId);
        return list;
    }

    @Override
    public Dept findById(String deptId) {
        return deptDao.findById(deptId);
    }

    @Override
    public void saveDept(Dept dept) {
        //表的id都不是自动生成的，通过UUID随机生成
        String id = UUID.randomUUID().toString();
        dept.setDeptId(id);

        //调用dao进行保存
        deptDao.save(dept);
    }

    @Override
    public int findParentCount(String deptId) {
        int count = deptDao.findParentCount(deptId);
        return  count;
    }

    @Override
    public boolean deleteDeptById(String deptId) {
        //先查询该部门的直接下级部门的数量（也就是将该部门作为上级部门的部门数）
        int count = deptDao.findParentCount(deptId);
        //根据返回值count判断是否有下级部门
        if(count==0){//没有给其他部门作上级，执行删除
            deptDao.deleteById(deptId);
            return true;    //删除成功
        }else{
            return false;   //是其他部门的上级部门，删除失败
        }
    }


    @Override
    public void updateDept(Dept dept) {
        //与保存的区别 》1：前者insert 后者是update  》2：前者需要产生id，后者有id
        deptDao.update(dept);
    }




}
