package com.fzu.bbs.controller;

import com.fzu.bbs.services.CommentServices;
import com.fzu.bbs.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentServices commentServices;

    @GetMapping("addComment")
    public R addCommnet(@RequestParam Map<String, Object> args) {
        String username = (String) args.get("username");
        String content = (String) args.get("content");
        Integer father = (Integer) args.get("father");
        Integer passageId = (Integer) args.get("passageId");
        commentServices.addComment(content, father, passageId, username);
        return R.ok();
    }
    @GetMapping("getCommentByPassageId")
    public R getCommentByPassageId(@RequestParam Map<String,Object> args){
        String IdStr  = (String)  args.get("id");
        Integer passageId = Integer.valueOf( IdStr );
        return R.ok(commentServices.getCommentByPassageId(passageId));
    }


}
