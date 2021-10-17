package com.fzu.bbs.services;


import com.fzu.bbs.po.MessageRecord;

public interface MessageRecordServices {
    //得到两个人的聊天记录
    MessageRecord getMessageRecord(Integer fromUserId, Integer toUserId);
    //删除两个人的聊天记录
    boolean clearMessageRecord(Integer fromUserId,Integer toUserId);
    //更新两个人的聊天记录
    boolean updateMessageRecord(MessageRecord messageRecord);
    //如果不存在记录用这个创建
    boolean createMessageRecord(Integer fromUserId,Integer toUserId);
    //追加聊天记录
    boolean addMessageRecord(Integer fromUserId,Integer toUserId,String addMessage);
}