package com.fzu.bbs.utils;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.fzu.bbs.BbsApplication.class})
public class OssTest {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

//    @Test
//    public void testOss(){
//        File file = new File("C:\\Users\\HBY\\Desktop\\test.jpg");
//        System.out.println(aliyunOSSUtil.uploadFile(file));
//    }

}
