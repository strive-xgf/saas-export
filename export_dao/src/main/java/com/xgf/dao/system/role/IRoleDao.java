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
}
