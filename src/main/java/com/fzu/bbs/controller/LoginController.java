package com.fzu.bbs.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.fzu.bbs.mapper.UserMapper;
import com.fzu.bbs.po.User;
import com.fzu.bbs.services.UserServices;
import com.fzu.bbs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
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

    @PostMapping(value = "/register")
    @ResponseBody
    @ApiOperation("注册")
    public R register(@RequestBody Map<String,Object> args){
        User user = new User();
        String userName = (String) args.get("name");
        String password = (String)  args.get("password");
        user.setUserName(userName);
        user.setPassword(password);
        userServices.addUser(user);
        return R.ok();
    }
    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "/edit")
    @ResponseBody
    @ApiOperation("注册")
    public R edit(@RequestBody Map<String,Object> args){
        Integer id = (Integer) args.get("id");
        String academy = (String) args.get("academy");
        String phone = (String) args.get("phone");
        String userName = (String) args.get("userName");
        User user = userServices.getUserById(id);
        user.setAcademy(academy);
        user.setPhone(phone);
        user.setUserName(userName);
        userMapper.updateById(user);
        return R.ok();
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
    @ApiOperation("注销账号")
    public R logOut(){
        if(StpUtil.isLogin()){
            StpUtil.logout();
        }
        return R.ok();
    }

}
