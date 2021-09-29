package com.fzu.bbs.controller;

import com.fzu.bbs.services.FollowServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {
    @Autowired
    private FollowServices followServices;

    @GetMapping("addFollow")
    public R addFollow(String from,String to){
        followServices.addFollow(from,to) ;
        return R.ok();
    }
    @GetMapping("delFollow")
    public R delFollow(String from,String to){
        followServices.deleteFollow(from,to);
        return R.ok();
    }
}
