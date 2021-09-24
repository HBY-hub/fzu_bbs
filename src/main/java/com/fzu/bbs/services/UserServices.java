package com.fzu.bbs.services;

import com.fzu.bbs.po.User;

import java.util.List;

public interface UserServices {
    boolean addUser(User user);
    boolean updateUserPqssword(int id,String password);
    User getUserById(int id);
    List<User> getUserByName(String username);
}
