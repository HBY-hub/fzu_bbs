package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.bbs.mapper.UserMapper;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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
    public boolean updateUserPassword(Integer id, String password) {
        User user = userMapper.selectById(id);
        if(user.getPassword()==password){
            return false;
        }
        user.setPassword(password);
        userMapper.updateById(user);
        return true;
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    //根据名字模糊查询,名字短的在前
    @Override
    public List<User> getUserByName(String username) {
        List<User> userList = new ArrayList();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .like("user_name",username);
//        queryWrapper.eq("user_name",username);
        userList = userMapper.selectList(queryWrapper);
        userList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getUserName().length()-o2.getUserName().length();
            }
        });
        return userList;
    }

    @Override
    public Integer checkUser(String username,String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        User user = userMapper.selectOne(queryWrapper);

        if(user==null){
            return 0;
        }
        if(!user.getPassword().equals(password))return -1;
        return user.getId();
    }
}
