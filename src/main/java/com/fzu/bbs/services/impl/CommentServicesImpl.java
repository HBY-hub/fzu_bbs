package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.bbs.mapper.CommentMapper;
import com.fzu.bbs.po.Comment;
import com.fzu.bbs.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServicesImpl implements CommentServices {
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public boolean addComment(String content, Integer father, Integer passageId, String username) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPassage(Math.toIntExact(passageId));
        comment.setFather(Math.toIntExact(father));
        comment.setUser_name(username);
        commentMapper.insert(comment);
        return false;
    }

    @Override
    public boolean deleteComment(Integer commentId) {
        if(commentMapper.deleteById(commentId)>0){
            return true;
        }else
            return false;
    }
}
