package com.xgf.service.system.module.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;


import com.xgf.dao.system.module.IModuleDao;
import com.xgf.domain.system.module.Module;
import com.xgf.service.system.module.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//模块业务实现类
@Service
public class ModuleServiceImpl implements IModuleService {
    @Autowired
    IModuleDao iModuleDao;

    @Override
    public PageInfo<Module> findByPage(int curr, int pageSize) {
        //设置分页参数  当前页和页面大小
        PageHelper.startPage(curr,pageSize);
        //调用全查查找所有的模块
        List<Module> list = iModuleDao.findAll();
        //包装成PageInfo
        PageInfo<Module> pi = new PageInfo<>(list);
        return pi;
    }

    @Override
    public void saveModule(Module module) {
        //随机生成UUID字符串作为模块的id
        String uuid= UUID.randomUUID().toString();
        module.setModuleId(uuid);
        iModuleDao.save(module);
    }

    @Override
    public Module findModuleById(String moduleId) {
        return iModuleDao.findById(moduleId);
    }

    @Override
    public void updateModule(Module module) {
        iModuleDao.update(module);
    }

    @Override
    public boolean deleteModule(String moduleId) {
        //iModuleDao.deleteById(moduleId);
        //删除模块时，要先查询该模块的子买了块数量
        //子模块数量==0 ；表示没有子模块，删除之后对其他的数据没有影响，可以删除
        //子模块数量 > 0 ；表示有子模块，删除会影响到其他数据的parentId找不到数据，所以删除失败
        int count = iModuleDao.findParentIdCount(moduleId);
        if (count==0){
            //没有子模块，可以删除
            iModuleDao.deleteById(moduleId);
            return true;
        }else{
            return false;   //存在子模块删除失败
        }
    }

    @Override
    public List<Module> findAllModules() {
        return iModuleDao.findAll();
    }

   /* @Override
    public List<Module> findModuleByRoleId(String roleId) {

        return iModuleDao.findByRoleId(roleId);
    }

    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        //先做删除 指定角色在中间表中的记录
        iModuleDao.deleteRoleModule(roleId);
        //moduleIds 202,203
        String[] mids = moduleIds.split(",");
        if (mids.length > 0) { //判断，再操作
            //再作添加
            for (String mid : mids) {
                iModuleDao.saveRoleModule(roleId, mid);
            }
        }
    }*/
}
