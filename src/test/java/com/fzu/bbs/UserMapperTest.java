package com.fzu.bbs;

import com.fzu.bbs.mapper.UserMapper;
import com.fzu.bbs.po.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        List<User> userList= userMapper.selectList(null);

        userList.forEach(System.out::println);
    }
}
