package com.fzu.bbs.controller;

import com.fzu.bbs.po.Passage;
import com.fzu.bbs.services.PassageServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@Api(tags = {"Hello"})
public class HelloController {
    @Autowired
    PassageServices passageServices;
    @GetMapping("test")
    @ApiOperation("Hello")
    public String Hello(){
        List<Passage> passageList = passageServices.getLatestPassage(1,1,"");
        return passageList.toString();
    }

}
