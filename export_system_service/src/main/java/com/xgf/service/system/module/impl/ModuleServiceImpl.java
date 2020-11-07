package com.xgf.service.system.module.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;


import com.xgf.dao.system.module.IModuleDao;
import com.xgf.domain.system.module.Module;
import com.xgf.domain.system.user.User;
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

    @Override
    public List<Module> findModuleByRoleId(String roleId) {
        return iModuleDao.findByRoleId(roleId);
    }

    //更新角色的模块信息
    @Override
    public void updateRoleModule(String roleId, String moduleIds) {
        //先做删除，删除角色在中间表（角色模块表）中的记录
        iModuleDao.deleteRoleModule(roleId);
        // zTree获取所有的模块id，按,逗号分开  moduleIds 202,203
        //判断该角色权限是否清空（该角色还有模块的情况，否则会抛出InvocationTargetException异常）
        if(moduleIds!=null && moduleIds != ""){
            String[] mids = moduleIds.split(",");
            //更新角色模块
            if (mids.length > 0) { //判断选择模块权限是否>0，再操作
                //再作添加，将模块赋予给角色（角色模块表添加记录）
                for (String mid : mids) {
                    iModuleDao.saveRoleModule(roleId, mid);
                }
            }
        }else{
            System.out.println("updateRoleModule 该用户没有任何权限了");
        }
    }

/*    //查找用户的模块权限信息
    @Override
    public List<Module> findModulesByUser(User user) {
        //degree ==0 平台管理员 只能看 Sass菜单
        //degree ==1 企业管理员 只能看 Sass菜单以外
        //degree ==其他 用户员 根据RBAC表查询
        //给一个用户数据到service，service自己判断
        if (user.getDegree() == 0) {//平台管理员
            return iModuleDao.findByBelong("0");
        } else if (user.getDegree() == 1) {//企业管理员
            return iModuleDao.findByBelong("1");
        } else {
            return iModuleDao.findByUserId(user.getUserId());
        }

    }*/
}
