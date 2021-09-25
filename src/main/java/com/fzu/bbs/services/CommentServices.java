package com.fzu.bbs.services;

import com.fzu.bbs.po.Comment;

public interface CommentServices {
    boolean addComment(String content,Integer father,Integer passageId,String username);
    boolean deleteComment(Integer commentId);
}
