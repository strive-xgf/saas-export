package com.xgf.service.system.module;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.system.module.Module;
import com.xgf.domain.system.user.User;

import java.util.List;

//模块业务结构
public interface IModuleService {
    //分页显示模块
    PageInfo<Module> findByPage(int curr, int pageSize);

    void saveModule(Module module);

    Module findModuleById(String moduleId);

    void updateModule(Module module);

    boolean deleteModule(String moduleId);

    //查找所有模块
    List<Module> findAllModules();
    //通过角色查找从属于该角色的所有模块
    List<Module> findModuleByRoleId(String roleId);
    //通过角色id更新角色的模块信息（zTree树形，先删除该角色的所有模块权限，再添加）
    void updateRoleModule(String roleId, String moduleIds);

    //查找用户的模块权限，显示左侧导航栏
    List<Module> findModulesByUser(User user);
}
