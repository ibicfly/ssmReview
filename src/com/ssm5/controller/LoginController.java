package com.ssm5.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssm5.dao.UserDao;
import com.ssm5.entity.User;
import com.ssm5.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/index")
	public String login(HttpServletRequest request,HttpServletResponse response,User userEntity)
	{
		//测试mybatis是否成功能访问数据库
		User user=userService.findUserByUserName("admin");
		String username=userEntity.getUsername();
		String password=userEntity.getPassword();
		if(userService.validatePassword(username, password))
		{
			return "login";
		}else
		{
			return "error";
		}
	}
}
