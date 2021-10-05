package com.fzu.bbs.services;

import com.fzu.bbs.services.CommentServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServicesTest {
    @Autowired
    CommentServices commentServices;

    @Test
    public void testDelete(){
        commentServices.deleteComment(2);
    }
}