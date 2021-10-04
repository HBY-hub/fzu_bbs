package com.fzu.bbs.controller;

import com.fzu.bbs.services.FollowServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FollowController {
    @Autowired
    private FollowServices followServices;

    @GetMapping("addFollow")
    public R addFollow(@RequestParam Map<String,Object> args){
        String from = (String) args.get("from");
        String to = (String) args.get("to");
        followServices.addFollow(from,to) ;
        return R.ok();
    }
    @GetMapping("delFollow")
    public R delFollow(@RequestParam Map<String,Object> args){
        String from = (String) args.get("from");
        String to = (String) args.get("to");
        followServices.deleteFollow(from,to);
        return R.ok();
    }
}
