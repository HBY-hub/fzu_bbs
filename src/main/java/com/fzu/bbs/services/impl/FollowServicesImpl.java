package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.bbs.mapper.FollowMapper;
import com.fzu.bbs.mapper.UserMapper;
import com.fzu.bbs.po.Follow;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.FollowServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowServicesImpl implements FollowServices {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FollowMapper followMapper;

    @Override
    public boolean addFollow(String fromUser,String toUser) {

        Follow follow = new Follow();

        follow.setFrom(fromUser);

        follow.setTo(toUser);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",toUser);
        follow.setToId(userMapper.selectOne(queryWrapper).getId());

        queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",fromUser);
        follow.setFromId(userMapper.selectOne(queryWrapper).getId());
        return true;
    }

    @Override
    public boolean deleteFollow(String fromUser, String toUser) {
        QueryWrapper<Follow> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("from",fromUser);
        queryWrapper.eq("to",toUser);
        followMapper.delete(queryWrapper);
        return true;
    }

}
