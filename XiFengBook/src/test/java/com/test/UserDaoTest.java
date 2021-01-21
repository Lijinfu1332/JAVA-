package com.test;

import com.XiFeng.dao.UserDao;
import com.XiFeng.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoTest {
    @Test
    public void queryUserByUsername(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
       UserDao userMapper= context.getBean("userMapper", UserDao.class);
        User user = userMapper.queryUserByUsername("admin");
        System.out.println(user);
    }
    @Test
    public void queryUserByUsername_Password(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserDao userMapper= context.getBean("userMapper", UserDao.class);
        User user = userMapper.queryUserByUsernameAndPassword("admin","admin");
        System.out.println(user);
    }
    @Test
    public void insertUser(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserDao userMapper= context.getBean("userMapper", UserDao.class);
        int in = userMapper.saveUser(new User(null, "zhadngsan", "123456", "auto@qq.com"));
        System.out.println(in);
    }
}
