package com.fzu.bbs.mapper;

import com.fzu.bbs.mapper.CommentMapper;
import com.fzu.bbs.mapper.UserMapper;
import com.fzu.bbs.po.Comment;
import com.fzu.bbs.po.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CommentMapperTest {
    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void testSelect(){
        List<Comment> commentList= commentMapper.selectList(null);

        commentList.forEach(System.out::println);
    }
}
