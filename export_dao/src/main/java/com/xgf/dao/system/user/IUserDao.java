package com.xgf.dao.system.user;


import com.xgf.domain.system.user.User;

import java.util.List;

public interface IUserDao {
    //根据公司id，查找所有用户，按照更新时间排序
    List<User> findAll(String companyId);
    //添加新增一个user
    void save(User user);
    //通过id查找用户
    User findById(String userId);
    //更新user信息
    void update(User user);
    //通过id删除user
    void deleteById(String userId);


    User finByEmail(String email);
}
