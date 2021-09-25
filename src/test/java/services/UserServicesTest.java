package services;


import com.fzu.bbs.po.User;
import com.fzu.bbs.services.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.fzu.bbs.BbsApplication.class})
public class UserServicesTest {
    @Autowired
    UserServices userServices;
    @Test
    public void testGetUserByName(){
        List<User> userList = userServices.getUserByName("zhangsan");
        userList.forEach(System.out::println);

    }
}
