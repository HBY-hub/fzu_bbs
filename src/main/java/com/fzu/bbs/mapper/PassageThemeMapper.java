package com.fzu.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.po.PassageTheme;
import com.fzu.bbs.po.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PassageThemeMapper extends BaseMapper<PassageTheme> {
}
