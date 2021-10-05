package com.fzu.bbs.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.UserServices;
import com.fzu.bbs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = {"login"})
public class LoginController {
    @Autowired
    UserServices userServices;

    @PostMapping(value = "/login")
    @ResponseBody
    @ApiOperation("登录")
    public R getLoginToken(@RequestBody Map<String,Object> args){
        User user = JSON.parseObject(JSON.toJSONString(args),User.class);
        Integer id  = userServices.checkUser(user.getUserName(),user.getPassword());
        if(id==0){
            return R.fail("用户不存在");
        }
        if(id<0){
            return R.fail("密码错误");
        }
        StpUtil.login(id);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return R.ok(tokenInfo);
    }
    @GetMapping("/login")
    @ResponseBody
    @ApiOperation("获得当前登录用户")
    public R getUser(@RequestParam Map<String,Object> args){
        String token = (String) args.get("token");
        Integer id = Integer.parseInt((String) StpUtil.getLoginIdByToken(token));
        User user = userServices.getUserById(id);
        if(user==null)return R.fail();
        return R.ok(user);
    }

    @GetMapping("/logout")
    @ResponseBody
    @ApiOperation("注销账号")
    public R logOut(){
        StpUtil.logout();
        return R.ok();
    }

}
