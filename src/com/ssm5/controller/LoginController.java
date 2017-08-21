package com.ssm5.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssm5.dao.UserDao;
import com.ssm5.entity.User;
import com.ssm5.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	//用户登录功能
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request,HttpServletResponse response,User userEntity)
	{
		//测试mybatis是否成功能访问数据库
		User user=userService.findUserByUserName("admin");
		String username=userEntity.getUsername();
		String password=userEntity.getPassword();
		if(userService.validatePassword(username, password))
		{
			if(user.getUsername().equals("admin"))
			{
	            request.getSession().setAttribute("username", user.getUsername());
				return "UserManger";
			}
            request.getSession().setAttribute("username", user.getUsername());
			return "login";
		}else
		{
			request.setAttribute("error",new String("用户名或密码错误"));
			return "error";
		}
	}  
	// 用户注销功能
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        return "index";
    }
    @RequestMapping(value = "/registerPage")
    public String registerDirect() {
        return "register";
    }
    // 用户注册
    @RequestMapping(value = "/register", method =POST)
    public String userRegister(User userRegister, HttpServletRequest request) {
        User user = userRegister;
        if (user != null) {
            try {
                String username = user.getUsername();
                // 如果数据库中没有该用户，可以注册，否则跳转页面
                if (userService.findUserByUserName(username) == null) {
                    // 添加用户
                    userService.addUser(user);
                    // 注册成功跳转
                    request.setAttribute("username", username);
                    return "main";
                } else {
                    request.setAttribute("error", "注册失败，用户名已被占用！");
                    return "login";
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "发生未知错误！");
                return "error";
            }
        }
        request.setAttribute("error", "发生未知错误！");
        return "error";
    }

	  
}
