package com.fzu.bbs;

import com.fzu.bbs.mapper.PassageMapper;
import com.fzu.bbs.mapper.UserMapper;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.po.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PassageMapperTest {
    @Autowired
    private PassageMapper passageMapper;

    @Test
    public void testSelect(){
        List<Passage> passageList= passageMapper.selectList(null);

        passageList.forEach(System.out::println);
    }
}
