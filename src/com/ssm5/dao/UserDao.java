package com.ssm5.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssm5.entity.User;

@Repository("UserDao")
public interface UserDao {
    public User findUserByUserName(String username);
    
    public String findPasswordByUserName(String username);
    
    public User findUserByUserId(int uId);

    public void addUser(User user);

//    public void deleteUserByUserName(String username);
//
//    public void updateUserByUserName(User user);
//
//    public List<User> getAllUserInfo();
}
