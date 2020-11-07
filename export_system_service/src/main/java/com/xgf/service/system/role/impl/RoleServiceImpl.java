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


/* 用户角色表中间表 */
    @Override
    public List<Role> findAll(String companyId) {
        return iRoleDao.findAll(companyId);
    }

    @Override
    public List<Role> findRolesByUserId(String userId) {
        return iRoleDao.findByUserId(userId);
    }

    //更新用户的角色表，需要先删除用户角色表，然后再保存新的
    @Override
    public void updateUserRole(String userId, String[] roleIds) {
        //先做删除，删除角色在中间表（用户角色表）中的记录
        iRoleDao.deleteUserRoleByUserId(userId);//删除 用户角色中间表

        //判断角色是否清空（清空角色，也就相当于没有了角色的权限，没有任何权限  类似于封号）
        if(roleIds != null && roleIds.length>0){
            //循环遍历添加用户的角色
            for(String roleId:roleIds){
                iRoleDao.saveUserRole(userId,roleId);
            }
        }else {
            System.out.println("userId = "+ userId + "的用户没有任何角色权限了");
        }
    }

}
