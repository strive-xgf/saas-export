package com.xgf.service.system.role.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xgf.dao.system.role.IRoleDao;
import com.xgf.domain.system.role.Role;
import com.xgf.service.system.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    IRoleDao iRoleDao;
    @Override
    public PageInfo<Role> findByPage(int curr, int pageSize, String companyId) {
        //设置分页参数
        PageHelper.startPage(curr,pageSize);
        //调用全查
        List<Role> list = iRoleDao.findAll(companyId);
        //包装成PageInfo发送给前台jsp
        PageInfo<Role> pi = new PageInfo<>(list);
        return pi;
    }

    @Override
    public void saveRole(Role role) {
        String uuid= UUID.randomUUID().toString();
        role.setRoleId(uuid);
        iRoleDao.save(role);
    }

    @Override
    public Role findById(String roleId) {
        return iRoleDao.findById(roleId);
    }

    @Override
    public void updateRole(Role role) {
        iRoleDao.update(role);
    }

    @Override
    public void deleteRole(String roleId) {
        iRoleDao.deleteById(roleId);
    }
}
