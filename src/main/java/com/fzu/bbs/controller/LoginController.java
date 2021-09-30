package com.fzu.bbs.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.UserServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserServices userServices;

    @PostMapping("/login")
    @ResponseBody
    public R getLoginToken(String username,String password){
        Integer id  = userServices.checkUser(username,password);
        if(id<0)return R.fail();
        StpUtil.login(id);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return R.ok(tokenInfo);
    }
    @GetMapping("/login")
    @ResponseBody
    public R getUser(String token){
        Integer id = Integer.parseInt((String) StpUtil.getLoginIdByToken(token));
        User user = userServices.getUserById(id);
        if(user==null)return R.fail();
        return R.ok(user);
    }

}
