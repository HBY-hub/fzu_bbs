package com.fzu.bbs.controller;

import com.alibaba.fastjson.JSON;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.po.Theme;
import com.fzu.bbs.services.PassageServices;
import com.fzu.bbs.services.PassageThemeServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PassageController {
    @Autowired
    private PassageServices passageServices;
    @Autowired
    PassageThemeServices passageThemeServices;

    @GetMapping("addPassage")
    @ResponseBody
    public R addPassage(@RequestBody Map<String,Object> args){
        System.out.println(args);
        Passage passage  = JSON.parseObject(JSON.toJSONString(args),Passage.class);
        String theme = (String) args.get("theme");
        passageServices.addPassage(passage);
        passageThemeServices.addPassageTheme(passage.getTitle(),theme);
        return R.ok();
    }
    @GetMapping("getHotPassage")
    public R getHotPassage(@RequestBody Map<String,Object> args){
        Integer num = (Integer) args.get("num");
        Integer page = (Integer) args.get("page");
        String theme = (String) args.get("theme");
        List passageList = passageServices.getLatestPassage(num,page,theme);
        if(passageList==null)return R.fail();
        return R.ok(passageList);
    }
}
