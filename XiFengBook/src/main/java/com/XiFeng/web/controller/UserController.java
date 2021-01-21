package com.XiFeng.web.controller;

import com.XiFeng.pojo.User;
import com.XiFeng.service.UserService;
import com.XiFeng.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {
    //依赖注入
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username, @Param("password") String password, Model model) throws Exception{
        // 调用 userService.login()登录处理业务
    	String uname=username;
    	String pword=password;
    	System.out.println(uname+"="+pword);
        if(uname==null&&pword==null) {
        	System.out.println("执行该方法");
        	model.addAttribute("msg","用户名和密码不能为空");
        	//model.setViewName("/user/login");
        	return "/user/login";
        }else {
        	 User loginUser = userService.login(username,password);
        // 如果等于null,说明登录 失败!
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中           
            model.addAttribute("msg","用户或密码错误！");    
            model.addAttribute("username", username);        
            //   跳回登录页面           
            return "/user/login";
        } else {
            // 登录 成功
            //跳到成功页面login_success.html
            model.addAttribute("user",loginUser);      
           return "/user/login_success";
        }}
    }
    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/logout")
    protected void logout(HttpServletRequest req, HttpServletResponse resp,SessionStatus status) throws ServletException, IOException {
//        1、销毁Session中用户登录的信息（或者销毁Session）
        status.setComplete();
//        2、重定向到首页（或登录页面）。
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理注册的功能
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/regist")
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

//        2、检查 验证码是否正确
        if (token!=null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名[" + username + "]已存在!");

                // 把回显信息，保存到Request域中
                req.setAttribute("msg", "用户名已存在！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

//        跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(new User(null, username, password, email));
//
//        跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "验证码错误！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            // System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }
    @RequestMapping("/ajaxExist")
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数username
        String username = req.getParameter("username");
        // 调用userService.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        // 把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }
}
