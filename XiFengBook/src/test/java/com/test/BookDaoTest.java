package com.test;

import com.XiFeng.dao.BookDao;
import com.XiFeng.pojo.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoTest {
    @Test
    public  void addBookTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        int i = bookMapper.addBook(new Book(null,"国哥为什么这么帅！", "191125", new BigDecimal(9999),1100000,0,null
        ));
        System.out.println(i);

    }
    @Test
    public  void deleteBookByIdTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        int i = bookMapper.deleteBookById(22);
        System.out.println(i);

    }
    @Test
    public  void updateBookTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        int i = bookMapper.updateBook(new Book(11,"大家都可以这么帅！", "国哥", new BigDecimal(9999),1100000,0,null
        ));
        System.out.println(i);
    }
    @Test
    public  void queryBookByIdTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        Book i = bookMapper.queryBookById(11);
        System.out.println(i);

    }
    @Test
    public  void queryBookListTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        List<Book> books = bookMapper.queryBooks();
        for (Book book:books
             ) {
            System.out.println(book);
        }
    }
    @Test
    public  void totalCountBookTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        Integer count = bookMapper.queryForPageTotalCount();
        System.out.println(count);
    }
    @Test
    public  void pageSizeBookTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        List<Book> books = bookMapper.queryForPageItems(1, 2);
        for (Book book:books
             ) {
            System.out.println(book);
        }
    }
    @Test
    public  void queryPriceBookTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        Integer i = bookMapper.queryForPageTotalCountByPrice(50, 100);
        System.out.println(i);
    }
    @Test
    public  void queryPriceBookPageTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        BookDao bookMapper = context.getBean("bookMapper", BookDao.class);
        List<Book> books = bookMapper.queryForPageItemsByPrice(1, 5, 50, 100);
        for (Book book:books
             ) {
            System.out.println(book);

        }
    }
}
