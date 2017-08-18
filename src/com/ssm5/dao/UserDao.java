package com.ssm5.dao;

import org.springframework.stereotype.Repository;

import com.ssm5.entity.User;

@Repository("UserDao")
public interface UserDao {
    public User findUserByUserName(String username);
    public String findPasswordByUserName(String username);
}
