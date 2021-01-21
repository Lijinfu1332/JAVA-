package com.XiFeng.service.impl;

import com.XiFeng.dao.UserDao;
import com.XiFeng.pojo.User;
import com.XiFeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }
    @Override
    public User login(String username,String password) {
        /**
         * 登录
         */
        return userDao.queryUserByUsernameAndPassword(username,password);
    }
//判断用户名是否存在
    @Override
    public boolean existsUsername(String username) {

        if (userDao.queryUserByUsername(username) == null) {
           // 等于null,说明没查到，没查到表示可用
           return false;
        }

        return true;

    }
}
