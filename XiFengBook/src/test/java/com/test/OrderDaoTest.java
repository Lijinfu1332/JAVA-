package com.test;

import com.XiFeng.dao.OrderDao;
import com.XiFeng.pojo.Order;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoTest {
    @Test
    public void OrderTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        OrderDao orderMapper = context.getBean("orderMapper", OrderDao.class);
        int i = orderMapper.saveOrder(new Order("1234567855",new Date(),new BigDecimal(100),1, 1));
        System.out.println(i);
    }
}
