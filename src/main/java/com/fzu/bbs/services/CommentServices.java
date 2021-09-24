package com.fzu.bbs.services;

import com.fzu.bbs.po.Comment;

public interface CommentServices {
    boolean addComment(String content,int father,Long passageId,String username);
    boolean deleteComment(Long commentId);
}
