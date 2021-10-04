package com.fzu.bbs.controller;

import com.alibaba.fastjson.JSON;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.po.Theme;
import com.fzu.bbs.services.PassageServices;
import com.fzu.bbs.services.PassageThemeServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class PassageController {
    @Autowired
    private PassageServices passageServices;
    @Autowired
    private PassageThemeServices passageThemeServices;

    @GetMapping("addPassage")
    @ResponseBody
    public R addPassage(@RequestParam Map<String,Object> args){
        System.out.println(args);
        Passage passage  = JSON.parseObject(JSON.toJSONString(args),Passage.class);
        String theme = (String) args.get("theme");
        passageServices.addPassage(passage);
        passageThemeServices.addPassageTheme(passage.getTitle(),theme);
        return R.ok();
    }
    @GetMapping("getHotPassage")
    public R getHotPassage(HttpServletRequest httpRequest){
        Map<String, String[]> args = httpRequest.getParameterMap();
        Integer num = Integer.valueOf(args.get("num")[0]);
        Integer page = Integer.valueOf(args.get("page")[0]);
        String theme=new String("");
        List passageList = passageServices.getLatestPassage(num,page,theme);
        if(passageList==null)return R.fail();
        return R.ok(passageList);
    }
    @GetMapping("getPassageById")
    public R getPassageById(@RequestParam Map<String,Object> args){
        String IdStr = (String) args.get("id");
        Integer id  = Integer.valueOf(IdStr);
        Passage passage = passageServices.getPassageById(id);
        return R.ok(passage);
    }
}
