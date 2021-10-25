package com.fzu.bbs.controller;

import com.alibaba.fastjson.JSON;
import com.fzu.bbs.po.Passage;
import com.fzu.bbs.po.Theme;
import com.fzu.bbs.services.ImageServices;
import com.fzu.bbs.services.PassageServices;
import com.fzu.bbs.services.PassageThemeServices;
import com.fzu.bbs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = {"Passage"})
public class PassageController {
    @Autowired
    private PassageServices passageServices;
    @Autowired
    private PassageThemeServices passageThemeServices;
    @Autowired
    private ImageServices imageServices;

    @PostMapping("addPassage")
    @ResponseBody
    @ApiOperation("增加一篇文章")
    public R addPassage(@RequestBody Map<String,Object> args){
        System.out.println(args);
        Passage passage  = JSON.parseObject(JSON.toJSONString(args),Passage.class);
        String theme = (String) args.get("theme");
        Integer id = passageServices.addPassage(passage);
        for(int i=0;i<((List<String>)args.get("image")).size();i++){
//            System.out.println(((List<String>) args.get("image")).get(i));
            imageServices.addImage(passage.getId(), ((List<String>) args.get("image")).get(i));
        }

        passageThemeServices.addPassageTheme(passage.getTitle(),theme);
        return R.ok();
    }


    @ApiOperation("得到最新文章")
    @GetMapping("getHotPassage")
    public R getHotPassage(HttpServletRequest httpRequest){
        Map<String, String[]> args = httpRequest.getParameterMap();
        Integer num = Integer.valueOf(args.get("num")[0]);
        Integer page = Integer.valueOf(args.get("page")[0]);
        String theme=args.get("theme")[0];
        List<Passage> passageList = passageServices.getLatestPassage(num,page,theme);
        if(passageList==null)return R.fail();
        return R.ok(passageList);
    }


    @ApiOperation("通过id得到文章")
    @GetMapping("getPassageById")
    public R getPassageById(@RequestParam Map<String,Object> args){
        String IdStr = (String) args.get("id");
        Integer id  = Integer.valueOf(IdStr);
        Passage passage = passageServices.getPassageById(id);
        return R.ok(passage);
    }
}
