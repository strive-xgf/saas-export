package com.xgf.dao.system.role;

import com.xgf.domain.system.role.Role;

import java.util.List;

//角色dao
public interface IRoleDao {
    List<Role> findAll(String companyId);
    void save(Role role);
    Role findById(String roleId);
    void update(Role role);
    void deleteById(String roleId);

    //通过userid查询用户的角色列表
    List<Role> findByUserId(String userId);
    //通过userid删除用户的所有角色
    void deleteUserRoleByUserId(String userId);
    //保存用户的一个角色
    void saveUserRole(String userId, String roleId);

}
