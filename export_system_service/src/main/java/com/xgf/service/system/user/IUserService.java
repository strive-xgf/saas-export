package com.xgf.service.system.user;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.system.user.User;


import java.util.List;

//user业务接口
public interface IUserService {
    //分页显示用户信息
    PageInfo<User> findByPage(int curr, int pageSize, String companyId);

    void saveUser(User user);

    User findUserById(String userId);

    void updateUser(User user);

    boolean deleteUser(String userId);

    List<User> findAllUsers(String companyId);
}
