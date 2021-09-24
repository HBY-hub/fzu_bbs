package com.fzu.bbs.services;

import com.fzu.bbs.po.User;

import java.util.List;

public interface FollowServices {
    boolean addFollow(User fromUser,User toUser);
    boolean deleteFollow(User fromUser,User toUser);
}
