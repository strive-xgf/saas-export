package com.xgf.service.system.role;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.system.role.Role;

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
}
