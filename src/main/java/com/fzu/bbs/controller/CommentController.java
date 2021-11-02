package com.fzu.bbs.controller;

import com.fzu.bbs.services.CommentServices;
import com.fzu.bbs.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags={"Comment"})
public class CommentController {
    @Autowired
    private CommentServices commentServices;

    @GetMapping("addComment")
    @ApiOperation("增加一条评论")
    public R addCommnet(@RequestParam Map<String, Object> args) {
        String username = (String) args.get("username");
        String content = (String) args.get("content");
        Integer father = Integer.valueOf((String) args.get("father"));
        Integer passageId = Integer.valueOf((String) args.get("passageId"));
        commentServices.addComment(content, father, passageId, username);
        return R.ok();
    }
    @GetMapping("getCommentByPassageId")
    @ApiOperation("获得一个文章下的所有评论")
    public R getCommentByPassageId(@RequestParam Map<String,Object> args){
        String IdStr  = (String)  args.get("id");
        Integer passageId = Integer.valueOf( IdStr );
        return R.ok(commentServices.getCommentByPassageId(passageId));
    }


}
