package com.XiFeng.web.controller;
import com.XiFeng.pojo.Book;
import com.XiFeng.pojo.Page;
import com.XiFeng.service.BookService;
import com.XiFeng.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    /**
     * 澶勭悊鍒嗛〉鍔熻兘
     * @param req
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    protected ModelAndView page(HttpServletRequest req)  {
        //1 鑾峰彇璇锋眰鐨勫弬鏁� pageNo 鍜� pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 璋冪敤BookService.page(pageNo锛宲ageSize)锛歅age瀵硅薄
        Page<Book> pages = bookService.page(pageNo,pageSize);

        pages.setUrl("book/page");
        ModelAndView model=new ModelAndView();
        //3 淇濆瓨Page瀵硅薄鍒癛equest鍩熶腑
         model.addObject("page", pages);
        //4 璇锋眰杞彂鍒皃ages/client/book_manager.jsp椤甸潰
        model.setViewName("client/index");
        return model;
    }
    /**
     * 鏍规嵁浠锋牸鏌ヨ
     * 澶勭悊鍒嗛〉鍔熻兘
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "pageByPrice")
    protected ModelAndView pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 鑾峰彇璇锋眰鐨勫弬鏁� pageNo 鍜� pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2 璋冪敤BookService.page(pageNo锛宲ageSize)锛歅age瀵硅薄
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb = new StringBuilder("book/pageByPrice.action");
        // 濡傛灉鏈夋渶灏忎环鏍肩殑鍙傛暟,杩藉姞鍒板垎椤垫潯鐨勫湴鍧�鍙傛暟涓�
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        // 濡傛灉鏈夋渶澶т环鏍肩殑鍙傛暟,杩藉姞鍒板垎椤垫潯鐨勫湴鍧�鍙傛暟涓�
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        System.out.println(page.getUrl());
        ModelAndView model=new ModelAndView();
        //3 淇濆瓨Page瀵硅薄鍒癛equest鍩熶腑
       model.addObject("page",page);
        //4 璇锋眰杞彂鍒皃ages/manager/book_manager.jsp椤甸潰
        model.setViewName("/client/index");
       return model;
    }
    @RequestMapping(value = "/bookManager",method = RequestMethod.GET)
    protected ModelAndView bookManager(HttpServletRequest req)  {
        //1 鑾峰彇璇锋眰鐨勫弬鏁� pageNo 鍜� pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 璋冪敤BookService.page(pageNo锛宲ageSize)锛歅age瀵硅薄
        Page<Book> pages = bookService.page(pageNo,pageSize);

        pages.setUrl("book/bookManager");
        ModelAndView model=new ModelAndView();
        //3 淇濆瓨Page瀵硅薄鍒癛equest鍩熶腑
        model.addObject("page", pages);
        //4 璇锋眰杞彂鍒皃ages/client/book_manager.jsp椤甸潰
        model.setViewName("/manager/book_manager");
        return model;
    }

    /**
     * 娣诲姞鍥句功
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/add")
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;
        //        1銆佽幏鍙栬姹傜殑鍙傛暟==灏佽鎴愪负Book瀵硅薄
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
//        2銆佽皟鐢˙ookService.addBook()淇濆瓨鍥句功
        bookService.addBook(book);
//        3銆佽烦鍒板浘涔﹀垪琛ㄩ〉闈�
//                /manager/bookServlet?action=list
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);

        resp.sendRedirect(req.getContextPath() + "/book/bookManager.action?pageNo=" + pageNo);

    }


@RequestMapping("/delete")
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1銆佽幏鍙栬姹傜殑鍙傛暟id锛屽浘涔︾紪绋�
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//        2銆佽皟鐢╞ookService.deleteBookById();鍒犻櫎鍥句功
        bookService.deleteBookById(id);
//        3銆侀噸瀹氬悜鍥炲浘涔﹀垪琛ㄧ鐞嗛〉闈�
//                /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/book/bookManager.action?pageNo=" + req.getParameter("pageNo"));
    }


    @RequestMapping(value = "/update")
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1銆佽幏鍙栬姹傜殑鍙傛暟==灏佽鎴愪负Book瀵硅薄
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
//        2銆佽皟鐢˙ookService.updateBook( book );淇敼鍥句功
        System.out.println(book);
//        2銆佽皟鐢˙ookService.updateBook( book );淇敼鍥句功
        bookService.updateBook(book);
//        3銆侀噸瀹氬悜鍥炲浘涔﹀垪琛ㄧ鐞嗛〉闈�
//        鍦板潃锛�/宸ョ▼鍚�/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/book/bookManager.action?pageNo=" + req.getParameter("pageNo"));
    }


@RequestMapping("/getBook")
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1 鑾峰彇璇锋眰鐨勫弬鏁板浘涔︾紪鍙�
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2 璋冪敤bookService.queryBookById鏌ヨ鍥句功
        Book book = bookService.queryBookById(id);
        //3 淇濆瓨鍒板浘涔﹀埌Request鍩熶腑
        req.setAttribute("book", book) ;
        //4 璇锋眰杞彂鍒般�俻ages/manager/book_edit.jsp椤甸潰
        req.getRequestDispatcher("/pages/manager/book_update.jsp").forward(req,resp);
    }


    @RequestMapping("/manager")
    protected ModelAndView list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 閫氳繃BookService鏌ヨ鍏ㄩ儴鍥句功
        List<Book> books = bookService.queryBooks();
        //2 鎶婂叏閮ㄥ浘涔︿繚瀛樺埌Request鍩熶腑
        ModelAndView model=new ModelAndView();
        model.addObject("books",books);
        //3銆佽姹傝浆鍙戝埌/pages/manager/book_manager.jsp椤甸潰
        model.setViewName("/manager/book_manager");
        return model;
    }

}
