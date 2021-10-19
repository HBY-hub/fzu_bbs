package com.fzu.bbs.services;


import com.fzu.bbs.po.MessageRecord;

import java.util.List;

public interface MessageRecordServices {
    //获得两个人所有聊天记录
    List<MessageRecord> getMessageRecord(Integer fromUserId,Integer toUserId);
    //删除两个人所有聊天记录
    boolean deleteMessageRecord(Integer fromUserId,Integer toUserId);
    //增加一条纯文本聊天记录
    boolean addMessageRecord(Integer fromUserId,Integer toUserId,String messageContext);
    //增加一条记录，是一个文件/图片路径
    boolean addFileRecord(Integer fromUserId,Integer toUserId,String messageContext);
    //用id删除一条聊天记录
    boolean deleteMessageRecord(Integer messageRecordId);
}