package com.xgf.dao.system.module;


import com.xgf.domain.system.module.Module;

import java.util.List;

public interface IModuleDao {
    //查找所有的功能模块
    List<Module> findAll();
    //新增一个功能模块  需要判断是否有上级模块，如果没有上级模块，赋值为NULL
    void save(Module module);
    //通过id查找模块
    Module findById(String moduleId);
    //更新模块信息
    void update(Module module);
    //通过id删除模块
    void deleteById(String moduleId);
    //查找以当前模块为父模块的模块数量
    int findParentIdCount(String moduleId);

    //通过角色id查询角色的模块权限
    List<Module> findByRoleId(String roleId);
    //删除指定角色的所有模块权限
    void deleteRoleModule(String roleId);
    //给角色添加模块(权限)
    void saveRoleModule(String roleId, String mid);

/*    //查找belong从属
    List<Module> findByBelong(String belong);//0平台管理1企业管理
    //查找用户的id
    List<Module> findByUserId(String userId);//使用RBAC*/
}
