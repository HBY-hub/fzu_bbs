package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.bbs.mapper.UserMapper;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(User user) {
        if(userMapper.selectById(user.getId())!=null){
            return false;
        }
        userMapper.insert(user);
        return true;
    }

    @Override
    public boolean updateUserPassword(Long id, String password) {
        User user = userMapper.selectById(id);
        if(user.getPassword()==password){
            return false;
        }
        user.setPassword(password);
        userMapper.updateById(user);
        return true;
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getUserByName(String username) {
        List<User> userList = new ArrayList();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        userList = userMapper.selectList(queryWrapper);
        return userList;
    }
}
