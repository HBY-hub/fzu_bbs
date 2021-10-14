package com.fzu.bbs.controller;

import com.fzu.bbs.services.FollowServices;
import com.fzu.bbs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags = {"Follow"})
public class FollowController {
    @Autowired
    private FollowServices followServices;

    @GetMapping("addFollow")
    @ApiOperation("增加关注关系")
    public R addFollow(@RequestParam Map<String,Object> args){
        String from = (String) args.get("from");
        String to = (String) args.get("to");
        followServices.addFollow(from,to) ;
        return R.ok();
    }
    @GetMapping("delFollow")
    @ApiOperation("删除关注关系")
    public R delFollow(@RequestParam Map<String,Object> args){
        String from = (String) args.get("from");
        String to = (String) args.get("to");
        followServices.deleteFollow(from,to);
        return R.ok();
    }
}
