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
    public boolean addFollow(User fromUser, User toUser) {
        Follow follow = new Follow();
        follow.setFrom(fromUser.getUserName());
        follow.setTo(toUser.getUserName());
        follow.setFromId(fromUser.getId());
        follow.setToId(toUser.getId());
        return false;
    }

    @Override
    public boolean deleteFollow(User fromUser, User toUser) {
        return false;
    }
}
