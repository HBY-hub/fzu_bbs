package com.fzu.bbs.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.UserServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserServices userServices;

    @PostMapping(value = "/login")
    @ResponseBody
    public R getLoginToken(@RequestBody Map<String,Object> args){
        User user = JSON.parseObject(JSON.toJSONString(args),User.class);
        Integer id  = userServices.checkUser(user.getUserName(),user.getPassword());
        if(id<0)return R.fail();
        StpUtil.login(id);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return R.ok(tokenInfo);
    }
    @GetMapping("/login")
    @ResponseBody
    public R getUser(@RequestParam Map<String,Object> args){
        String token = (String) args.get("token");
        Integer id = Integer.parseInt((String) StpUtil.getLoginIdByToken(token));
        User user = userServices.getUserById(id);
        if(user==null)return R.fail();
        return R.ok(user);
    }

}
