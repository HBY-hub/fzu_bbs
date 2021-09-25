package com.fzu.bbs.controller;

import com.fzu.bbs.po.Passage;
import com.fzu.bbs.services.PassageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class HelloController {
    @Autowired
    PassageServices passageServices;
    @GetMapping("test")
    public String Hello(){
        List<Passage> passageList = passageServices.getLatestPassage(1,1);
        return passageList.toString();
    }

}
