package com.fzu.bbs.services.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.bbs.mapper.CommentMapper;
import com.fzu.bbs.po.Comment;
import com.fzu.bbs.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //删除一个评论顺便把其子评论一起删除,便于维护
    @Override
    public boolean deleteComment(Integer commentId) {
        List<Comment> comments = getCommentsWithFatherId(commentId);
        for (Comment comment : comments) {
            deleteComment(comment.getId());
        }
        if(commentMapper.deleteById(commentId)>0){
            return true;
        }else
            return false;
    }

    //获得fatherId为fatherId的comments
    @Override
    public List<Comment> getCommentsWithFatherId(Integer fatherId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper
                .eq("father",fatherId);
        List<Comment> comments = commentMapper.selectList(wrapper);
        return comments;
    }

    @Override
    public List<Comment> getCommentByPassageId(Integer passageId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("passage",passageId);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return comments;
    }
}
