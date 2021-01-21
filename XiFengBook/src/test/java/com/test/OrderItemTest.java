package com.test;

import com.XiFeng.dao.OrderItemDao;
import com.XiFeng.pojo.OrderItem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class OrderItemTest {
    @Test
    public void orderItemAddTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        OrderItemDao orderItemMapper = context.getBean("orderItemMapper", OrderItemDao.class);
        int i = orderItemMapper.saveOrderItem(new OrderItem(null,"java从入门到精通", 4,new BigDecimal(100),new BigDecimal(100),"1234567855"));
        System.out.println(i);
    }
}
