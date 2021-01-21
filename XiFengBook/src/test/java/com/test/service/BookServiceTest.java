package com.test.service;

import com.XiFeng.pojo.Book;
import com.XiFeng.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;

public class BookServiceTest {
    @Test
    public void addBookServiceTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookService bookService = context.getBean("bookServiceImpl", BookService.class);
        bookService.addBook(new Book(null,"从入门到秃顶", "191125", new BigDecimal(9999),1100000,0,null
        ));
    }
}
