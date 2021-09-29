package com.fzu.bbs.controller;

import com.fzu.bbs.po.Passage;
import com.fzu.bbs.services.PassageServices;
import com.fzu.bbs.services.PassageThemeServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PassageController {
    @Autowired
    private PassageServices passageServices;
    @Autowired
    PassageThemeServices passageThemeServices;

    @GetMapping("addPassage")
    public R addPassage(String title,String description,String theme,String username){
        Passage passage = new Passage();
        passage.setTitle(title);
        passage.setDescription(description);
        passage.setUserName(username);
        passageServices.addPassage(passage);
        passageThemeServices.addPassageTheme(title,theme);
        return R.ok();
    }
    @GetMapping("getHotPassage")
    public R getHotPassage(Integer num,Integer page){
        List passageList = passageServices.getLatestPassage(num,page);
        if(passageList==null)return R.fail();
        return R.ok(passageList);
    }
}
