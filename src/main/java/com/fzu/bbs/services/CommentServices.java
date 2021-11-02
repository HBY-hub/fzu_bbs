package com.fzu.bbs.services;

import com.fzu.bbs.po.Comment;

import java.util.List;

public interface CommentServices {
    boolean addComment(String content,Integer father,Integer passageId,String username);
    boolean deleteComment(Integer commentId);
    List<Comment> getCommentsWithFatherId(Integer fatherId);
    List<Comment> getCommentByPassageId(Integer passageId);
    Comment getCommentById(Integer id);
}
