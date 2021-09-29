package com.fzu.bbs.controller;

import com.fzu.bbs.services.CommentServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentServices commentServices;

    @GetMapping("addComment")
    public R addCommnet (String username,String content,Integer father,Integer passageId){
        commentServices.addComment(content,father,passageId,username);
        return R.ok();
    }


}
