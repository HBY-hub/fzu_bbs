package com.fzu.bbs.services;


import com.fzu.bbs.po.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.fzu.bbs.BbsApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServicesTest {
    @Autowired
    UserServices userServices;
    @Test
    public void testGetUserByName(){
        List<User> userList = userServices.getUserByName("zhangsan");
        userList.forEach(System.out::println);

    }

    @Test
    public void test1(){
        User user = new User();
        user.setUserName("qiuxinhan");
        user.setPassword("123456");
        user.setEmail("120975969@qq.com");
        user.setPhone("13174549517");
        userServices.addUser(user);
    }
}
