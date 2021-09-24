package com.fzu.bbs.services;

import com.fzu.bbs.po.User;

import java.util.List;

public interface UserServices {
    boolean addUser(User user);
    boolean updateUserPqssword(Long id,String password);
    User getUserById(Long id);
    List<User> getUserByName(String username);
}
