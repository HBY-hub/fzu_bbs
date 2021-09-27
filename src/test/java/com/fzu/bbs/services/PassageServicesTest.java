package com.fzu.bbs.services;

import com.fzu.bbs.po.Passage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PassageServicesTest {

    @Autowired
    PassageServices passageServices;

    @Test
    public void selectTest(){
        List<Passage> passages = passageServices.getLatestPassage(5,2);
        passages.forEach(System.out::println);
    }

    @Test
    public void getPassageByName(){
        List<Passage> passages = passageServices.getPassagesByName("产后");
        System.out.println(passages.size());
        passages.forEach(System.out::println);
    }

}
