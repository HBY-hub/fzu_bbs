package com.fzu.bbs.mapper;

import com.fzu.bbs.mapper.ThemeMapper;
import com.fzu.bbs.mapper.UserMapper;
import com.fzu.bbs.po.Theme;
import com.fzu.bbs.po.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ThemeMapperTest {
    @Autowired
    private ThemeMapper themeMapper;

    @Test
    public void testSelect(){
        List<Theme> themeList= themeMapper.selectList(null);

        themeList.forEach(System.out::println);
    }
}
