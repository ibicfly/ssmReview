package com.ssm5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm5.dao.UserDao;
import com.ssm5.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	public User findUserByUserName(String username)
	{
		User user=null;
		user=userDao.findUserByUserName(username);
		return user;
	}
	public boolean validatePassword(String username,String password)
	{
		String act_password=userDao.findPasswordByUserName(username);
		if(act_password!=null&&password!=null&&act_password.equals(password))
		{
			return true;
		}else
		{
			return false;
		}
	}
}
