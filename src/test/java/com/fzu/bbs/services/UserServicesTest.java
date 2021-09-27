package com.fzu.bbs.services;

import com.fzu.bbs.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServicesTest {

    @Autowired
    UserServices userServices;

    @Test
    public void selectByName(){
        List<User> users = userServices.getUserByName("lisa");
        users.forEach(System.out::println);
    }
}
