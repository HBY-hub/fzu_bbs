package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.bbs.mapper.PassageMapper;
import com.fzu.bbs.mapper.PassageThemeMapper;
import com.fzu.bbs.mapper.ThemeMapper;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.po.PassageTheme;
import com.fzu.bbs.po.Theme;
import com.fzu.bbs.services.PassageThemeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassageThemeImpl implements PassageThemeServices {

    @Autowired
    ThemeMapper themeMapper;
    @Autowired
    PassageMapper passageMapper;
    @Autowired
    PassageThemeMapper passageThemeMapper;
    @Override
    public void addPassageTheme(String passage, String theme) {
        Integer passageId = passageMapper.selectOne(new QueryWrapper<Passage>().eq("title",passage)).getId();
        Integer themeId = themeMapper.selectOne(new QueryWrapper<Theme>().eq("theme",theme)).getId();

        PassageTheme passageTheme = new PassageTheme();
        passageTheme.setPassage(passage);
        passageTheme.setTheme(theme);
        passageTheme.setPassageId(passageId);
        passageTheme.setThemeId(themeId);
        passageThemeMapper.insert(passageTheme);
    }
}
