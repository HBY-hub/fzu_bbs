package com.fzu.bbs.services;

import com.fzu.bbs.po.Passage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {com.fzu.bbs.BbsApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PassageServicesTest {
    @Autowired
    PassageServices passageServices;

    @Test
    public void passagePageSelectTest (){
        List<Passage> passageList= passageServices.getLatestPassage(2,1,"");
        passageList.forEach(System.out::println);

    }
    @Test
    public void passageAddTest (){
        Passage passage = new Passage();
        passage.setContent("test");
        passage.setUserName("user");
        passage.setDescription("des");
        passage.setTheme("失物招领");
        passage.setTitle("tttitle");
        passageServices.addPassage(passage);

    }
}
