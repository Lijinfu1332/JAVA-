package com.test.service;

import com.XiFeng.pojo.User;
import com.XiFeng.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
    @Test
    public void login(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserService userServiceImpl = context.getBean("userServiceImpl", UserService.class);
        User login = userServiceImpl.login("admin", "admin");
        if (login!=null){
            System.out.println("登录成功");
        }
    }
}
