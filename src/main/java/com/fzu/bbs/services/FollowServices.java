package com.fzu.bbs.services;

import com.fzu.bbs.po.User;

import java.util.List;

public interface FollowServices {
    boolean addFollow(String fromUser,String toUser);
    boolean deleteFollow(String fromUser,String toUser);
}
