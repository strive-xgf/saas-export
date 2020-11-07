package com.xgf.service.system.role;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.system.role.Role;
import java.util.List;

//角色业务类
public interface IRoleService {
    //查找素有角色，并分页显示  参1：当前页  参2：每页数据大小 参3：公司id
    PageInfo<Role> findByPage(int curr, int pageSize, String companyId);
    //通过id查找角色
    Role findById(String roleId);
    //保存角色
    void saveRole(Role role);
    //更新角色
    void updateRole(Role role);
    //删除角色
    void deleteRole(String roleId);


/*多表 用户角色表 */
    //通过companyId查询该公司的所有角色列表（给用户授予角色的时候要显示所有角色列表，勾选授权）
    List<Role> findAll(String companyId);
    //通过userid查询用户的角色列表
    List<Role> findRolesByUserId(String userId);
    //更新用户的角色 （包括删除角色(deleteUserRoleByUserId)、新增角色两个(saveUserRole)）
    void updateUserRole(String userId, String[] roleIds);
}
