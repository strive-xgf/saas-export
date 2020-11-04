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

    /*List<Module> findByRoleId(String roleId);

    void deleteRoleModule(String roleId);
    void saveRoleModule(String roleId, String mid);*/
}
