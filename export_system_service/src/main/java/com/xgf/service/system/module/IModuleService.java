package com.xgf.service.system.module;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.system.module.Module;

import java.util.List;

//模块业务结构
public interface IModuleService {
    //分页显示模块
    PageInfo<Module> findByPage(int curr, int pageSize);

    void saveModule(Module module);

    Module findModuleById(String moduleId);

    void updateModule(Module module);

    boolean deleteModule(String moduleId);

    List<Module> findAllModules();

/*    List<Module> findModuleByRoleId(String roleId);

    void updateRoleModule(String roleId, String moduleIds);*/
}
