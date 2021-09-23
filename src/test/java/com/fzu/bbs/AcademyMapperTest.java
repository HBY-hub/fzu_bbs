package com.fzu.bbs;

import com.fzu.bbs.mapper.AcademyMapper;
import com.fzu.bbs.po.Academy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AcademyMapperTest {
    @Autowired
    private AcademyMapper academyMapper;

    @Test
    public void testSelect(){
        List<Academy> academyList= academyMapper.selectList(null);

        academyList.forEach(System.out::println);
    }
}
