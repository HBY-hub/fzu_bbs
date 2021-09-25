package com.fzu.bbs.services.impl;

import com.fzu.bbs.mapper.PassageThemeMapper;
import com.fzu.bbs.mapper.ThemeMapper;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.po.PassageTheme;
import com.fzu.bbs.services.ThemeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeServicesImpl implements ThemeServices {
    @Autowired
    private PassageThemeMapper passageThemeMapper;

    @Override
    public boolean addThemeToPassage(Integer themeId, Integer passageId, String theme, String passage) {
        PassageTheme passageTheme = new PassageTheme();
        passageTheme.setPassageId(passageId);
        passageTheme.setThemeId(themeId);
        passageTheme.setTheme(theme);
        passageTheme.setPassage(passage);
        Integer res = passageThemeMapper.insert(passageTheme);
        return res>0;
    }
}
