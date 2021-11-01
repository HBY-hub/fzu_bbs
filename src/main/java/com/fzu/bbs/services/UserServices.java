package com.fzu.bbs.services;

import com.fzu.bbs.po.User;

import java.util.List;

public interface UserServices {
    boolean addUser(User user);
    boolean updateUserPassword(Integer id,String password);
    User getUserById(Integer id);
    List<User> getUserByName(String username);
    Integer checkUser(String username,String password);
    void updateUserFace(Integer userId,String faceData);
    User checkFace(String face);
    boolean updateAvatar(Integer id,String url);
}
